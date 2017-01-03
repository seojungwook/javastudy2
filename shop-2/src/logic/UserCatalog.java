package logic;

public interface UserCatalog {
   void entryUser(User user);

   User getUserByIdAndPw(String userId, String password);
   
}
