package logic;

public interface Shop {
  void entryUser(User user);

User getUserByIdAndPw(String userId, String password);
}
