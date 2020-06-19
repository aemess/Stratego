package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class GameSpec extends WordSpec with Matchers {
  "A Game" when {
    val matchField = new MatchField(4, 4, false)
    val characList = CharacterList(4)
    val playerBlue = Player("PlayerBlue", characList.getCharacterList())
    val playerRed = Player("PlayerRed", characList.getCharacterList())
    val game = Game(playerBlue, playerRed, 4, matchField)

    val matchField6 = new MatchField(6, 6, false)
    val characList6 = CharacterList(6)
    val playerBlue6 = Player("PlayerBlue", characList6.getCharacterList())
    val playerRed6 = Player("PlayerRed", characList6.getCharacterList())
    val game6 = Game(playerBlue6, playerRed6, 6, matchField6)

    val matchField8 = new MatchField(8, 8, false)
    val characList8 = CharacterList(8)
    val playerBlue8 = Player("PlayerBlue", characList8.getCharacterList())
    val playerRed8 = Player("PlayerRed", characList8.getCharacterList())
    val game8 = Game(playerBlue8, playerRed8, 8, matchField8)

    val matchField10 = new MatchField(10, 10, false)
    val characList10 = CharacterList(10)
    val playerBlue10 = Player("PlayerBlue", characList10.getCharacterList())
    val playerRed10 = Player("PlayerRed", characList10.getCharacterList())
    val game10 = Game(playerBlue10, playerRed10, 10, matchField10)
    "created with two Players and a empty Matchfield" should {
      "fill the Matchfield with Characters" in {
        game.init().toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }
    "figure set" should {
      var board = game.aChar(matchField, 3, 2, 0)
      "out of bounds -> catch it" in{
        //game.outOfBounds(matchField, 0, 3) should be("The Figure can not set out of bounds\n!   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
      "not out of bound -> don't catch it" in{
        //game.outOfBounds(board,2,0) should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |  F  |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }
    "gets a matchfield with a blueList" should {
      "filled with characters" in {
        game.aChar(matchField, 0, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }
    "gets a matchfield with a redList" should {
      "filled with characters" in {
        game.bChar(matchField, 1, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  8  |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with blue Fields" should {
      "set a character" in {
        game6.setBlue(1,0,"F").toString should be("   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 0\n+-----+-----+-----+-----+-----+-----+\n|  F  |     |     |     |     |     | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 3\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 4\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with red Fields" should {
      "set a character" in {
        game6.setRed(5,0,"F").toString should be("   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 0\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 3\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 4\n+-----+-----+-----+-----+-----+-----+\n|  F  |     |     |     |     |     | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init
      val board1 = game.moveDown(board,0,0)
      val board2 = game.moveDown(board1,1,0)
      "and now one character moves down" in {
        game.moveDown(board, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|  9  |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveDown(board,3,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveDown(board,0,3).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        //game.moveDown(board2,2,0).toString should be()
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init()
      "and now one character moves up" in {
        game.moveUp(board, 3, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|  9  |     |     |     | 2\n+-----+-----+-----+-----+\n|     |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init()
      "and now one character moves to the left" in {
        game.moveLeft(board, 0, 1).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }
    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init()
      "and now one character moves to the right" in {
        game.moveRight(board, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      var board = game.aChar(matchField, 3, 3, 3)
      "and now one character moves to the right out of bounds" in {
        game.moveRight(board, 3, 3).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with characters in it" should {
      "convert the characters to values" in {
        game.characValue("1") should be(1)
        game.characValue("F") should be(0)
        game.characValue("B") should be(11)
        game.characValue("M") should be(10)
      }
    }

    "gets a matchfield with blue fields" should {

      "only set characters in blue fields" in {
        game6.isBlueField(2) should be(false)
        game8.isBlueField(3) should be(false)
        game10.isBlueField(4) should be(false)
      }
    }

    "gets a matchfield with red fields" should {
      "only set characters in red fields" in {
        game6.isRedField(3) should be(false)
        game8.isRedField(4) should be(false)
        game10.isRedField(5) should be(false)
      }
    }

    "gets a matchfield with characters" should {
      val board = game.init()
      "that are moving in directions" in {
        game.move('u',board,3,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|  9  |     |     |     | 2\n+-----+-----+-----+-----+\n|     |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with characters" should {
      val board = game.init()
      "that has values" in {
        game.figureHasValue(board,0,0) should be(9)
      }
    }

    "gets a matchfield with characters" should {
      "attack each others" in {
        game.Context.attack(matchField,0,0,0,1).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }
  }
}
