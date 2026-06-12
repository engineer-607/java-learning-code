package com.nanxinda.extendsExercise;

public class Test {
    public static void main(String[] args) {
        PC pc = new PC("intel","DDR4","1TB","联想");
        NotePad notePad = new NotePad("intel","DDR4","1TB","black");
        pc.showBInfo();
        notePad.showCINfo();
    }
}
