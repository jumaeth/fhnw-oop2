package exceptions;

import java.io.IOException;

public class Throws {
    public void foo1() {
//        throw new IOException();
    }

    public void foo2() {
        throw new NullPointerException();
    }

    public void foo3() throws Exception {
        throw new IOException();
    }

    public void foo4() throws RuntimeException {
//        throw new IOException();
    }

    public void foo5() throws Exception {
        throw new NullPointerException();
    }

    public void foo6() throws IOException {
//        throw new Exception();
    }

    public void foo7() throws NullPointerException {
        throw new RuntimeException();
    }
}
