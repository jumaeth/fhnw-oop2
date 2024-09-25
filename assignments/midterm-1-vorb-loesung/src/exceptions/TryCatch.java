package exceptions;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class TryCatch {

    public void foo1() {
        try {
            throw new NoSuchFileException("foo.txt");
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    public void foo2() {
//        try {
//            throw new IOException();
//        } catch (NoSuchFileException e) {
//            System.out.println("Exception");
//        }
    }

    public void foo3() {
        try {
            throw new IndexOutOfBoundsException();
        } catch (NullPointerException e) {
            System.out.println("Exception");
        }
    }

    public void foo4() {
//        try {
//            throw new NullPointerException();
//        } catch (IOException e) {
//            System.out.println("Exception");
//        }
    }

    public void foo5() {
//        try {
//            throw new NullPointerException();
//        }
    }
}
