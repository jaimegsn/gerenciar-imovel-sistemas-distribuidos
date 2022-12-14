import java.lang.reflect.Method;


// Reflection
class ClasseTest {
    public static void imprime(String mensagem) {
        System.out.println(mensagem);
    }
}

public class tests {
    public static void main(String[] args) {
        try {
            Class<?> variavel = Class.forName("ClasseTest");
            Method method = variavel.getMethod("imprime", String.class);

            Object[] parametros = new Object[1];
            parametros[0] = "Alo";
            method.invoke(variavel,parametros);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

