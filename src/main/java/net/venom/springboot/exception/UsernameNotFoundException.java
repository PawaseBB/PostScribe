package net.venom.springboot.exception;

public class UsernameNotFoundException extends Exception{

    public UsernameNotFoundException(String s){
        super(s);
    }
}
