package sample.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * SpringPersonHasFieldは、自身のメンバー(SpringDog)をフィールドインジェクションしているクラス。
 * SpringPersonHasFieldを生成すると、メンバー(SpringDog)がDIコンテナにより挿入される。
 */
@SpringJUnitConfig(classes = AppConfig.class) // 忘れやすいので注意
public class SpringPersonHasFieldTest {
    @Autowired
    private SpringPersonHasField cut;

    /**
     * Autowiredで生成。メンバー(SpringDog)はDIコンテナが注入してくれる。
     */
    @Test
    void test1() {
        System.out.println(cut.getGreeting());
    }

    /**
     * getBeanで生成する。メンバー(SpringDog)はDIコンテナが注入してくれる。
     */
    @Test
    void test2() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            SpringPersonHasField person = context.getBean(SpringPersonHasField.class);
            System.out.println(person.getGreeting());
        }
    }

    /**
     * 普通にnewするとDIコンテナによるメンバー(SpringDog)の注入がされないのでエラーになる。
     */
    @Test
    void test3() {
        SpringPersonHasField person = new SpringPersonHasField();
        System.out.println(person.getGreeting());
    }
}
