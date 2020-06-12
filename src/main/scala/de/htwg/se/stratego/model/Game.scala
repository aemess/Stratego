package de.htwg.se.stratego.model
import de.htwg.se.stratego.model
import de.htwg.se.stratego.model.{Game, MatchField, Player}


case class Game(playerA: Player, playerB: Player, size: Int, var matchField: MatchField) {
  val bList = playerA.characterList
  val rList = playerB.characterList


  def init(): MatchField = {

    //sets Characters of PlayerA
    var row = 0
    var col = 0
    // shuffle(aList)
    for (charac <- bList) {
      if (row.equals(size)) {
        col += 1
        row = 0
      }
      matchField = matchField.addChar(col, row, charac)
      row += 1
    }

    //Sets Characters of PlayerB
    row = 0
    col = size - 1
    // shuffle(bList)
    for (charac <- rList) {
      if (row.equals(size)) {
        col -= 1
        row = 0
      }
      matchField = matchField.addChar(col, row, charac)
      row += 1
    }
    matchField
  }

  def characValue(charac:String): Int = {
    if (charac.matches("[1-9]")) {
      return charac.toInt
    }
    charac match {
      case "B" => 11
      case "M" => 10
      case "F" => 0
    }
  }

  def isBlueField(row:Int): Boolean = {
    matchField.fields.matrixSize match {
      case 4 | 5 => if(row > 0) false else true
      case 6 | 7 => if(row > 1) false else true
      case 8 | 9 => if(row > 2) false else true
      case 10    => if(row > 3) false else true
    }
  }

  def isRedField(row:Int): Boolean = {
    matchField.fields.matrixSize match {
      case 4 | 5 => if(row < 3) false else true
      case 6 | 7 => if(row < 4) false else true
      case 8 | 9 => if(row < 5) false else true
      case 10    => if(row < 6) false else true
    }
  }

  def setBlue(row:Int, col:Int, charac: String): MatchField = {
    if (isBlueChar(charac) && isBlueField(row) && !matchField.fields.field(row,col).isSet) {
      matchField = matchField.addChar(row,col,bList(bList.indexOf(GameCharacter(Figure.FigureVal(charac,characValue(charac))))))
      return matchField
    }
    matchField
  }

  def isBlueChar(charac:String): Boolean = {
    bList.foreach(GameCharacter =>
      if(GameCharacter.toString().equals(charac)) {
        return true
      }
    )
    false
  }

  def setRed(row:Int, col:Int, charac: String): MatchField = {
    if (isRedChar(charac) && isRedField(row) && !matchField.fields.field(row,col).isSet) {
      matchField = matchField.addChar(row,col,rList(rList.indexOf(GameCharacter(Figure.FigureVal(charac,characValue(charac))))))
      //bList.updated(idx, GameCharacter(Figure.FigureVal(charac,value)))
      return matchField
    }
    matchField
  }

  def isRedChar(charac:String): Boolean = {
    bList.map(GameCharacter => if(GameCharacter.figure.name.equals(charac)) return true else false)
    false
  }

  def aChar(matchfield: MatchField, idx: Int, row: Int, col: Int): MatchField = matchfield.addChar(row, col, bList(idx))

  def bChar(matchfield: MatchField, idx: Int, row: Int, col: Int): MatchField = matchfield.addChar(row, col, rList(idx))

  def moveDown(matchField: MatchField, row: Int, col: Int): MatchField = {
    if (row == size - 1) {
      println("The Figure can not set out of bounds!")
      return matchField
    }
    if (isFlagOrBomb(row,col)) {
      println("Flag and Bombs can't move!")
      return matchField
    }
    if (matchField.fields.field(row + 1, col).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row + 1, col, matchField.fields.field(row, col).character.get)
    } else {
      val f = matchField.fields.field(row + 1, col)
      println(s"Field (${row + 1},$col) is set with Figure $f!")
      matchField
    }
  }

  def moveUp(matchField: MatchField, row: Int, col: Int): MatchField = {
    if (row == 0) {
      println("The Figure can not set out of bounds!")
      return matchField
    }
    if (isFlagOrBomb(row,col)) {
      println("Flag and Bombs can't move!")
      return matchField
    }
    if (matchField.fields.field(row - 1, col).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row - 1, col, matchField.fields.field(row, col).character.get)
    } else {
      val f = matchField.fields.field(row - 1, col)
      println(s"Field (${row - 1},$col) is set with Figure $f!")
      matchField
    }
  }

  def moveLeft(matchField: MatchField, row: Int, col: Int): MatchField = {
    if (col == 0) {
      println("The Figure can not set out of bounds!")
      return matchField
    }
    if (isFlagOrBomb(row,col)) {
      println("Flag and Bombs can't move!")
      return matchField
    }
    if (matchField.fields.field(row, col - 1).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row, col - 1, matchField.fields.field(row, col).character.get)
    } else {
      val f = matchField.fields.field(row, col - 1)
      println(s"Field ($row,${col - 1}) is set with Figure $f!")
      matchField
    }
  }

  def moveRight(matchField: MatchField, row: Int, col: Int): MatchField = {
    if (col == size - 1) {
      println("The Figure can not set out of bounds!")
      return matchField
    }
    if (isFlagOrBomb(row,col)) {
      println("Flag and Bombs can't move!")
      return matchField
    }
    if (matchField.fields.field(row, col + 1).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row, col + 1, matchField.fields.field(row, col).character.get)
    } else {
      val f = matchField.fields.field(row, col + 1)
      println(s"Field ($row,${col + 1}) is set with Figure $f!")
      matchField
    }
  }

  def isFlagOrBomb(row: Int,col: Int): Boolean = if(matchField.fields.field(row,col).character.get.figure.value == 0 ||
    matchField.fields.field(row,col).character.get.figure.value == 11) true else false

  def figureHasValue(matchF: MatchField, row: Int,col: Int): Int = {
    matchF.fields.field(row,col).character.get.figure.value
  }

  def attack(matchField: MatchField, rowA: Int, colA: Int, rowD: Int, colD: Int): MatchField = {
    println("Attack:" + matchField.fields.field(rowA,colA).character.get.figure.value)
    println("Defence:" + matchField.fields.field(rowD,colD).character.get.figure.value)
    println("rowDistance: "+ math.abs(rowA-rowD) + " colDistance: "+ math.abs(colA-colD))

    if(((Math.abs(rowA-rowD)>1)||(Math.abs(colA-colD)>1))||((Math.abs(rowA-rowD)==1)&&(Math.abs(colA-colD)==1))){ //field of attacked character is too far away
      return matchField
    }
    if(matchField.fields.field(rowA, colA).isSet.equals(true) && matchField.fields.field(rowD, colD).isSet.equals(true)){ //both fields are set
      if(figureHasValue(matchField, rowA,colA).equals(11)|figureHasValue(matchField,rowA,colA).equals(0)){ //flag and bomb are unable to attack
        return matchField
      }
      if((figureHasValue(matchField, rowA,colA) == 1) && (figureHasValue(matchField, rowD, colD) == 10)){ //attacker is spy and attacked figure is marshal
        return matchField.removeChar(rowD, colD).addChar(rowD, colD, matchField.fields.field(rowA,colA).character.get).removeChar(rowA,colA)
      }
      if(figureHasValue(matchField, rowA,colA) > figureHasValue(matchField,rowD, colD)) {
        if(figureHasValue(matchField,rowD, colD)==0){ //attacked figure is flag
          println("Flag has been found! Game finished!")
        }
        return matchField.removeChar(rowD, colD).addChar(rowD, colD, matchField.fields.field(rowA,colA).character.get).removeChar(rowA,colA)
      }
      if(figureHasValue(matchField, rowD, colD).equals(11)){ //attacked figure is a bomb
        if(figureHasValue(matchField, rowA, colD) == 3) { //attacker is miner => just remove bomb
          return matchField.removeChar(rowD, colD)
        }
      }
      if (figureHasValue(matchField, rowA,colA) < figureHasValue(matchField,rowD, colD)) {
        return matchField.removeChar(rowA, colA)
      }
      return matchField.removeChar(rowA, colA).removeChar(rowD, colD) //else remove both figures
    }
    matchField
  }
}

