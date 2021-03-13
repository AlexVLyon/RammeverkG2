public  class Timer {

    public static float times = System.nanoTime();

    public static float getTimes() {
        return (float)((System.nanoTime() - times) * 1E-9);
    }
    public Timer(){

    }

}
