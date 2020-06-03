package de.htwg.se.stratego.controller

import de.htwg.se.stratego.util.Observable
import de.htwg.se.stratego.model.{CharacterList, Game, GameCharacter, MatchField, Player}

import scala.io.StdIn.readLine

class Controller(var matchField:MatchField) extends Observable {
  val list = CharacterList(matchField.fields.matrixSize)
  val playerBlue = Player("PlayerBlue", list.getCharacterList())
  val playerRed = Player("PlayerRed", list.getCharacterList())
  val game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)


  def createEmptyMatchfield(size:Int):Unit = {
    matchField = new MatchField(size,size,false)
    notifyObservers()
  }

  def initMatchfield(): Unit = {
    matchField = game.init()
    notifyObservers()
  }

  def addChar(row:Int, col:Int, charac:GameCharacter):Unit = {
    matchField = matchField.addChar(row,col,charac)
    notifyObservers()
  }

  def moveDown(row:Int, col:Int): Unit = {
    matchField = game.moveDown(matchField, row, col)
    notifyObservers()
  }

  def moveUp(row:Int, col:Int): Unit = {
    matchField = game.moveUp(matchField, row, col)
    notifyObservers()
  }

  def moveRight(row:Int, col:Int): Unit = {
    matchField = game.moveRight(matchField, row, col)
    notifyObservers()
  }

  def moveLeft(row:Int, col:Int): Unit = {
    matchField = game.moveLeft(matchField, row, col)
    notifyObservers()
  }

  def matchFieldToString: String = matchField.toString
}