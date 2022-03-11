module Tetris {
    requires javafx.fxml;
    requires javafx.controls;

    opens tetris to javafx.graphics;

    exports tetris;
}