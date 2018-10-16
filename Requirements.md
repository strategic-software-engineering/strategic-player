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

**Incomplete User Stories** <br />
* As a client, I want the Player class to adopt an optimal strategy for a 4 coin 2 reveal game so that the game can be finished in the smallest number of spins.<br />
  <I>Acceptance Criteria:</I><br />
  When I play a 4 coin 2 reveal game, it will be solved in the fewest number of spins possible.

* As a client, I want the beginGame method to not accept invalid values when it is called, so that the game is legitimate and playable.
  <I>Acceptance Criteria:</I><br />
  When invalid values are passed to beginGame method, end the game and throw an exception.

* As a client, I want statistical information for how likely the optimal strategy is to win, so that I know how effective the strategy is.
  <I>Acceptance Criteria:</I><br />
  There is statistical information about the worst case scenario in the JavaDoc for the Player class.

* As a developer, in the event that the pattern indicates that the first pair of revealed coins matches, in a 4 coin, 2 reveal game, I want the the getNewCoinStates method to flip both revealed coins to the opposite side, so that we have a 1 out of 3 chance of winning the game in the first round.
  <I>Acceptance Criteria:</I><br />
  The getNewCoinStates method flips both coins if they match on the first reveal.

* As a developer, during a 4 coin, 2 reveal game, I want the getSlotsToReveal method to return the pattern "??--" the first time the method is called during that game, and the pattern "?-?-" for all plays after that, so that we can guarantee that at least 3/4 coins will match in the first 2 turns.
  <I>Acceptance Criteria:</I><br />
  The getCoinsToReveal method returns the pattern "??--" the first time that it is called, and the pattern "?-?-" for all the calls after that.

**Completed User Stories** <br />
* As a client, I want the StrategicPlayer class to follow the specified interface so that I can use it as planned.  
  <I>Acceptance Criteria:</I><br />
  I can call each method in the interface with their specified arguments.
  
* As a client, I want the StrategicPlayer class to use any other strategy to play the game for any game that is not a 4 coin 2 reveal game, so that any type of game can be played with this class.<br />
  <I>Acceptance Criteria:</I><br />
  When I play any game other than 4 coin 2 reveal, the game will be played, but the strategy will not be guaranteed to be optimal or   victorious.
  
* As a developer, I want the beginGame method to initialize a game with the parameters user specifies so that the game is exactly what user wants it to be.<br />
  <I>Acceptance Criteria:</I><br />
  When beginGame method is called, a game with my specified parameters is created.
  
* As a developer, I want the getSlotsToReveal method to return a CharSequence that follows the correct format so that the game is being played correctly.<br />
  <I>Acceptance Criteria:</I><br />
  When getSlotsToReveal method is called, the returned CharSequence has the same number of characters as there are coins in the game,   and there will only be 2 character types: "-" and "?".
  
* As a developer, I want the getSlotsToReveal method to return a CharSequence that follows the rules so that the game is being played fairly.<br />
  <I>Acceptance Criteria:</I><br />
  When getSlotsToReveal method is called, the returned CharSequence will contain a number of "?" characters equal to revealsPerSpin.   The rest of the characters will be "-".
  
* As a developer, I want the getNewCoinStates method to accept a valid CharSequence as an argument that represents the current state of the wheel so that the player can play the game correctly.<br />
  <I>Acceptance Criteria:</I><br />
  When getNewCoinStates method is called, the argument I supply will be checked for format rules and to make sure the slots revealed   were the slots requested.
 
* As a developer, I want the getNewCoinStates method to return a CharSequence in the correct format with the player's changes to the coins in the wheel so that the player can play the game.<br />
  <I>Acceptance Criteria:</I><br />
  When getNewCoinStates method is called, I expect a valid (format and rules) CharSequence to be returned with any changes to the       revealed coins that the player has made.
  
* As a tester, I want the getNewCoinStates method not to attempt to modify non-revealed coins so that the game is fair.<br />
  <I>Acceptance Criteria:</I><br />
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
