package kr.hs.dgsw.web_01_319;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    List<User> userList;

    public UserServiceImpl(){
        userList = new ArrayList<>();
        userList.add(new User("user1","user111@dgsw.hs.kr"));
        userList.add(new User("user2","user222@dgsw.hs.kr"));
        userList.add(new User("user3","user333@dgsw.hs.kr"));

    }

    @Override
    public List<User> list(){
        return this.userList;
    }

    @Override
    public User view(String name) {
        return this.userList.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElse(null);

    }

    @Override
    public boolean add(User user){
        if(this.view(user.getName()) == null){
            return this.userList.add(user);
        }
        return false;
    }

    @Override
    public User update(User user) {
        User found = this.view(user.getName());
        if(found != null){
            found.setEmail(user.getEmail());
            return found;
        }
        return null;
    }

    @Override
    public boolean delete(String name) {
        User found = this.view(name);
        return this.userList.remove(found);
    }

    public User find1(String name){
        Iterator<User> userIterator = this.userList.iterator();
        while(userIterator.hasNext()){
            User user = userIterator.next();
            if(user.getName().equals(name))
                return user;
        }
        return null;
    }
    public User find2(String name){
        for(User user : this.userList){
            if(user.getName().equals(name))
                return user;
        }
        return null;
    }
    public User find3(String name){
        User found = this.userList.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElse(null);
        return found;
    }

    public String listString() {
        return this.userList.toString();
    }
}
