package com.github.ikhoon.test

import org.scalatest.FunSuite

import scala.concurrent.{ ExecutionContext, Future }

/**
 * Created by ikhoon on 2016. 5. 2..
 */

case class FutureSeq[+A](future: Future[Seq[A]]) {
  def flatMap[B](f: A => FutureSeq[B])(implicit ec: ExecutionContext): FutureSeq[B] =
    FutureSeq(future.flatMap {
      case a => Future.sequence(a.map(f andThen (_.future))).map(_.flatten)
    })

  def map[B](f: A => B)(implicit ec: ExecutionContext): FutureSeq[B] = FutureSeq(future.map(_.map(f)))

  def filter(p: A ⇒ Boolean)(implicit ec: ExecutionContext): FutureSeq[A] = withFilter(p)(ec)

  def withFilter(p: A ⇒ Boolean)(implicit ec: ExecutionContext): FutureSeq[A] =
    FutureSeq(future.map(_.filter(p)))
}
class FutureTransformerTest extends FunSuite {
  test("monad test") {
    import scala.concurrent.Future

    case class Order(id: Int, userId: Long, addr: String)
    case class Item(id: Int, name: String, price: Long)

    val orders = Map(
      1 -> List(Order(1, 1, "Seoul"), Order(2, 1, "Seoul")),
      2 -> List(Order(3, 2, "Tokyo"), Order(4, 2, "Tokyo")),
      3 -> List(Order(5, 3, "NewYork"), Order(6, 3, "NewYork")),
      4 -> List(Order(7, 4, "Daegu"), Order(8, 4, "Daegu"))
    )

    val orderItems = Map(
      1 -> List(Item(1, "I1", 2000), Item(2, "I2", 4000)),
      2 -> List(Item(2, "I2", 4000), Item(3, "I3", 6000), Item(4, "I4", 8000)),
      3 -> List(Item(3, "I3", 6000), Item(4, "I4", 8000), Item(5, "I5", 10000)),
      4 -> List(Item(4, "I4", 4000), Item(5, "I5", 10000), Item(6, "I6", 12000)),
      5 -> List(Item(1, "I1", 2000), Item(2, "I2", 4000), Item(3, "I3", 6000)),
      6 -> List(Item(2, "I2", 4000), Item(4, "I4", 8000)),
      7 -> List(Item(3, "I3", 6000), Item(4, "I4", 8000), Item(5, "I5", 10000), Item(6, "I6", 12000)),
      8 -> List(Item(4, "I4", 4000), Item(5, "I5", 10000), Item(7, "I7", 14000))
    )

    // 주문내역을 가져오는 API
    def getOrders(userId: Int)(implicit ec: ExecutionContext): Future[List[Order]] =
      Future.successful(orders.get(userId).get)
    // 주문의 상품 내역을 가져오는 API
    def getOrderItems(order: Order)(implicit ec: ExecutionContext): Future[List[Item]] =
      Future.successful(orderItems.get(order.id).get)

    import scala.concurrent.ExecutionContext.Implicits.global
    val userId = 1
    val user1_OrderItems: Future[List[Item]] = for {
      orderList: List[Order] <- getOrders(userId)
      itemList: List[Item] <- {
        val itemListFutureList: List[Future[List[Item]]] = for {
          order <- orderList
        } yield getOrderItems(order)
        val itemFutureListList: Future[List[List[Item]]] = Future.sequence(itemListFutureList)
        val itemFutureList: Future[List[Item]] = itemFutureListList.map(_.flatten)
        itemFutureList
      }
    } yield itemList

    user1_OrderItems.foreach(println)

    val user2_OrderItems = for {
      // 비동기로 유저의 상품내역을 가져옴
      order: Order <- FutureSeq(getOrders(userId))
      // 주문 상품내역을 추출하기 위해서는 List에서 Order값을 가져와야함
      item: Item <- FutureSeq(getOrderItems(order))
    } yield item

    user2_OrderItems.future.foreach(println)
  }

}
