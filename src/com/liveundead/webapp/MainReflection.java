package com.liveundead.webapp;

import com.liveundead.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws ReflectiveOperationException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid1");
        Method method = r.getClass().getMethod("toString");
        System.out.println(method.invoke(r));
    }
}
