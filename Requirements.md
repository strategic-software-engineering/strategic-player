**Description** <br />
This class will implement the specified interface and play the Coin Wheel Game.

**Repo URL** <br />
https://github.com/strategic-software-engineering/strategic-player

**How Strategic Player Will Play The Game** <br />
1. beginGame(int coinsPerWheel, int revealsPerSpin, int maxNumSpins) is called to start a new game with the given parameters.
1. getSlotsToReveal() is called, which decides which slots to reveal and returns a CharSequence with "?" marks in the slots to be revealed.
1. getNewCoinStates(CharSequence revealedPattern) is called. The parameter CharSequence is the current state of the wheel. The method decides to flip any of the revealed coins and returns the CharSequence.
1. The class will play a 4 coin, 2 reveal game in a strategic way to guarantee a win in the smallest number of spins possible.
1. The class will play a game with any other number of coins and reveals with any strategy. It does not have to guarantee a win.

**Software License** <br />
MIT License

**User Stories** <br />
1. As a client, I want the StrategicPlayer class to follow the specified interface so that I can use it as planned.
1. As a user, I want the StrategicPlayer class to adopt an optimal strategy for a 4 coin 2 reveal game so that the class can finish the game in the smallest number of spins.
1. As a user, I want the StrategicPlayer class to use any other strategy to play the game for any game that is not a 4 coin 2 reveal game, so that any type of game can be played with this class.
1. As a user, I want the StrategicPlayer class to use a CharSequence of a specific format to represent the wheel so that I can interact with the player and the game in a predictable way.
  * The CharSequence length will be the same as the number of coins in the slot, and each coin is represented by a character.
  * A revealed coin will be shown with either an H or a T, correlating with the coin being Heads or Tails, respectively.
  * A hidden coin will be represented with a "-".
  * A coin that the player is requesting to be revealed will be represented with a "?"
1. As a user, I want the beginGame method to initialize a game with the parameters I specify so that the game is exactly what I want it to be.
1. As a user, I want the getSlotsToReveal method to return a CharSequence that follows the rules so that the game is being played fairly.
  * The number of coins to reveal cannot exceed revealsPerSpin (and will not likely be fewer -- why would it ever be fewer?).
  * The number of characters in the CharSequence shall not exceed the number of coins in the wheel for the current game.
  * The CharSequence return shall not contain any characters other than "-" to represent a hidden coin and a "?" to represent a coin to reveal.
1. As a user, I want the getNewCoinStates method to accept a valid CharSequence as an argument that represents the current state of the wheel so that the player can play the game.
  * The number of characters in the CharSequence shall not exceed the number of coins in the wheel for the current game.
  * The coins that were selected to be revealed by getSlotsToReveal shall be revealed and represented by either an "H" or a "T". Hidden coins will be represented with a "-".
1. As a user, I want the getNewCoinStates method to return a valid CharSequence with the player's changes to the coins in the wheel so that the player can play the game.
  * All of the above rules for valid sequences apply.
  * Coins that were not revealed cannot be modified.

**Development Tools** <br />
1. Ant for building
1. JUnit 4
1. Git with GitHub for version control
1. Checkstyle
1. IntelliJ for development

**Team Members** <br />
- Egor Muscat | emuscat1@msudenver.edu
- Levi Portenier | lporteni@msudenver.edu