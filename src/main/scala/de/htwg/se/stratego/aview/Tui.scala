package de.htwg.se.stratego.aview

import de.htwg.se.stratego.util.Observer
import de.htwg.se.stratego.controller.Controller

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  val size = 4

  def processInputLine(input: String):Unit = {
    input match {
      case "q" =>
      case "n" => controller.createEmptyMatchfield(size)
      case "i" => controller.initMatchfield()
      case _ =>
        input.toList.filter(c=> c != ' ') match {
          case 'd' :: row :: col :: Nil => controller.moveDown(row.toString.toInt, col.toString.toInt)
          case 'u' :: row :: col :: Nil => controller.moveUp(row.toString.toInt, col.toString.toInt)
          case 'r' :: row :: col :: Nil => controller.moveRight(row.toString.toInt, col.toString.toInt)
          case 'l' :: row :: col :: Nil => controller.moveLeft(row.toString.toInt, col.toString.toInt)
          case 'a' :: rowA :: colA :: rowD :: colD :: Nil => controller.attack(rowA.toString.toInt,colA.toString.toInt,
            rowD.toString.toInt, colD.toString.toInt)
          case 'o' :: row :: col :: charac :: Nil => controller.setBlueField(row.toString.toInt,col.toString.toInt,charac.toString)
          case 't' :: row :: col :: charac :: Nil => controller.setRedField(row.toString.toInt,col.toString.toInt,charac.toString)
          case _ =>
        }
    }
  }
  override def update: Unit = println(controller.matchFieldToString)
}
