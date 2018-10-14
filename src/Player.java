public class Player implements StrategicPlayer {
    private int coinsPerWheel;
    private int revealsPerSpin;
    private int maxNumSpins;

    public void beginGame(int coinsPerWheel, int revealsPerSpin, int maxNumSpins){
        this.coinsPerWheel = coinsPerWheel;
        this.revealsPerSpin = revealsPerSpin;
        this.maxNumSpins = maxNumSpins;
    }

    public CharSequence getSlotsToReveal(){
        CharSequence a = new CharSequence(){
            @Override
            public int length() {
                return coinsPerWheel;
            }

            @Override
            public char charAt(int index) {
                return 0;
            }

            @Override
            public CharSequence subSequence(int start, int end) {
                return null;
            }
        };
    return a.toString();
    }

    public CharSequence getNewCoinStates(CharSequence revealedPattern){
        CharSequence b = new CharSequence() {
            @Override
            public int length() {
                return coinsPerWheel;
            }

            @Override
            public char charAt(int index) {
                return 0;
            }

            @Override
            public CharSequence subSequence(int start, int end) {
                return null;
            }
        };
    return b.toString();
    }
}
