**Description** <br />
This class will implement the specified interface and play the Coin Wheel Game.

**Repo URL** <br />
https://github.com/strategic-software-engineering/strategic-player

**How Strategic Player Will Play The Game** <br />
1. beginGame(int coinsPerWheel, int revealsPerSpin, int maxNumSpins) is called to start a new game with the given parameters.
2. getSlotsToReveal() is called, which decides which slots to reveal and returns a CharSequence with "?" marks in the slots to be revealed.
3. getNewCoinStates(CharSequence revealedPattern) is called. The parameter CharSequence is the current state of the wheel. The method decides to flip any of the revealed coins and returns the CharSequence.
4. The class will play a 4 coin, 2 reveal game in a strategic way to guarantee a win in the smallest number of spins possible.
5. The class will play a game with any other number of coins and reveals with any strategy. It does not have to guarantee a win.

**Software License** <br />
MIT License

**User Stories** <br />
1. As a client, I want the StrategicPlayer class to follow the specified interface so that I can use it as planned.  
  <I>Acceptance Criteria:</I><br />
  I can call each method in the interface with their specified arguments.
  
2. As a client, I want the StrategicPlayer class to adopt an optimal strategy for a 4 coin 2 reveal game so that the game can be finished in the smallest number of spins.<br />
  Acceptance Criteria:<br />
  When I play a 4 coin 2 reveal game, it will be solved in the fewest number of spins possible.
  
3. As a client, I want the StrategicPlayer class to use any other strategy to play the game for any game that is not a 4 coin 2 reveal game, so that any type of game can be played with this class.

  Acceptance Criteria:
  When I play any game other than 4 coin 2 reveal, the game will be played, but the strategy will not be guaranteed to be optimal or   victorious.
  
4. As a developer, I want the beginGame method to initialize a game with the parameters user specifies so that the game is exactly what user wants it to be.

  Acceptance Criteria:
  When beginGame method is called, a game with my specified parameters is created.
  
5. As a developer, I want the getSlotsToReveal method to return a CharSequence that follows the correct format so that the game is being played correctly.

  Acceptance Criteria:
  When getSlotsToReveal method is called, the returned CharSequence has the same number of characters as there are coins in the game,   and there will only be 2 character types: "-" and "?".
  
6. As a developer, I want the getSlotsToReveal method to return a CharSequence that follows the rules so that the game is being played fairly.

  Acceptance Criteria:
  When getSlotsToReveal method is called, the returned CharSequence will contain a number of "?" characters equal to revealsPerSpin.   The rest of the characters will be "-".
  
7. As a developer, I want the getNewCoinStates method to accept a valid CharSequence as an argument that represents the current state of the wheel so that the player can play the game correctly.

  Acceptance Criteria:
  When getNewCoinStates method is called, the argument I supply will be checked for format rules and to make sure the slots revealed   were the slots requested.
  
8. As a developer, I want the getNewCoinStates method to return a CharSequence in the correct format with the player's changes to the coins in the wheel so that the player can play the game.

  Acceptance Criteria:
  When getNewCoinStates method is called, I expect a valid (format and rules) CharSequence to be returned with any changes to the       revealed coins that the player has made.
  
9. As a tester, I want the getNewCoinStates method not to attempt to modify non-revealed coins so that the game is fair.

  Acceptance Criteria:
  When getNewCoinStates method is called, I expect the hidden coins (represented by "-") to remain unchanged.

**Development Tools** <br />
* Ant for building
* JUnit 4
* Git with GitHub for version control
* Checkstyle
* IntelliJ for development

**Team Members** <br />
- Egor Muscat | emuscat1@msudenver.edu
- Levi Portenier | lporteni@msudenver.edu
