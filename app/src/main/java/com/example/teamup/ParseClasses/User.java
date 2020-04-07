package com.example.teamup.ParseClasses;

import com.parse.ParseClassName;
import com.parse.ParseObject;

//----------------------------------------------------------------------------------
// Creating an object for each user
//----------------------------------------------------------------------------------
@ParseClassName("User")
public class User extends ParseObject {

        public static final String KEY_USER = "objectId";
        public static final String KEY_FIRSTNAME = "fname";
        public static final String KEY_LASTNAME = "lname";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_PASSWORD = "password";
        public static final String KEY_USERNAME = "username";
        //public static final String KEY_LOCATION = "location";
        public static final String KEY_IMAGE ="profileImage";

        public User() { super(); }

        public String getCustomerId() {
                return getObjectId();
        }

        public String getFirstName(){ return getString(KEY_FIRSTNAME); }
        public void setFirstName(String firstName){ put(KEY_FIRSTNAME, firstName); }

        public String getLastName(){ return getString(KEY_LASTNAME); }
        public void setLastName(String lastName){ put(KEY_LASTNAME, lastName); }

        public String getEmail(){ return getString(KEY_EMAIL); }
        public void setEmail(String email){ put(KEY_EMAIL, email); }

        public String getUsername(){ return getString(KEY_USERNAME); }
        public void setUsername(String user){ put(KEY_USERNAME, user); }

        public String getPassword(){ return getString(KEY_PASSWORD); }
        public void setPassword(String password){ put(KEY_PASSWORD, password); }

}
