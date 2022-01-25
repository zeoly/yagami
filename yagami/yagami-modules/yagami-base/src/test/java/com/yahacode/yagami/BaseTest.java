package com.yahacode.yagami;


import com.yahacode.yagami.base.mvc.SessionService;
import com.yahacode.yagami.core.model.Person;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {TestApplication.class})
public class BaseTest {

    @MockBean
    private SessionService sessionService;

    public void beforeMethod() {
        Person operator = new Person();
        operator.setCode("UNITTEST");
        Mockito.when(sessionService.getLoginPerson()).thenReturn(operator);
    }
}
