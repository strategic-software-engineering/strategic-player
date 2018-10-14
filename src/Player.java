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
        int count = revealsPerSpin;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<coinsPerWheel; i++){
            if (count>0) {
                stringBuilder.append('?');
                count--;
            }
            else
                stringBuilder.append('-');
        }
    return stringBuilder.toString();
    }

    public CharSequence getNewCoinStates(CharSequence revealedPattern){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<coinsPerWheel; i++){
            if (revealedPattern.charAt(i) == '?' )
                stringBuilder.replace(0,1,"H");
        }

        return stringBuilder.toString();
    }

}
