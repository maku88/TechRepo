package pl.maku.tech.guavaTest;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


        List<User> users = newArrayList(new User("maciek",1),
                new User("tomek",2), new User("romek",4));


        Collection<User> usersWithoutDigitsInLogin = filter(users, new ShouldHaveIdGraterThanTwo());

        for(User u : usersWithoutDigitsInLogin) {
            System.out.println(u.toString());
        }

    }




    public static class User {
        private String name;
        private int id;

        public User(String name, int id) {
            this.name = checkNotNull(name);
            this.id = id;
            checkArgument(id != 0);
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    public static class ShouldHaveIdGraterThanTwo implements Predicate<User> {

        @Override
        public boolean apply(@Nullable User user) {
            checkNotNull(user);
            return user.id > 2 ? true : false ;
        }
    }



}
