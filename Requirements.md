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
1. ...

**Development Tools** <br />
1. Ant for building
1. JUnit 4
1. Git with GitHub for version control
1. Checkstyle
1. IntelliJ for development

**Team Members** <br />
- Egor Muscat | emuscat1@msudenver.edu
- Levi Portenier | lporteni@msudenver.edu