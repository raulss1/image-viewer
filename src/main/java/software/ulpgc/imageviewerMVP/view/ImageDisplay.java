package software.ulpgc.imageviewerMVP.view;

import java.io.IOException;

public interface ImageDisplay {
    void paint(String id, int offset) throws IOException;
    int getWidth();
    void clear();
    void on(Shift shift);
    void on(Released released);

    interface Shift {
        Shift Null = offset -> {};
        void offset(int offset) throws IOException;
    }
    interface Released {
        Released Null = offset -> {};
        void offset(int offset) throws IOException;
    }
}
