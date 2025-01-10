package Controller;

public class SessionManager {
    private static boolean isLoggedIn = false;
    private static int userId = -1; 

    
    public static void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }


    public static void setUserId(int id) {
        userId = id;
    }

    
    public static int getUserId() {
        return userId;
    }
    
}
