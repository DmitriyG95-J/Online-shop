package online.shop.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Component
@Scope(scopeName = SCOPE_SESSION, proxyMode = TARGET_CLASS)//может лучше синглтон?
public class Cart {

    private Map<Long, Integer> products; // id и количество товаров

    public Cart() {
        this.products = new HashMap<>();
    }

    public Map<Long, Integer> getProducts() {
        return Collections.unmodifiableMap(products); // чтобы снаружи нельзя было изменить
    }

    public void add(Long productId) {
        products.merge(productId, 1, (o1, o2) -> o1 + o2);
    }

    public void delete() {
        products.clear();
    }
}
