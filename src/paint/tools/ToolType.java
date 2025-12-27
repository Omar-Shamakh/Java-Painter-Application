/* 
I first used public class with static final attributes but i searched and 
found that enum is a better in this case especially in debugging and compile 
time safety 
*/ 
package paint.tools;

public enum ToolType {
    LINE,
    RECTANGLE,
    OVAL,
    FREE_HAND,
    ERASER
}

/*
equivalent to 

public class ToolType {
    public static final int LINE = 0;
    public static final int RECTANGLE = 1;
    public static final int OVAL = 2;
    public static final int FREE_HAND = 3;
    public static final int ERASER = 4;
}

*/