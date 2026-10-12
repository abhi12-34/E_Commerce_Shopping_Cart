// jb product out stock hoga to ye exception throw hoga

public class ProductOutOfStockException extends Exception {
    public ProductOutOfStockException(String message) {
        super(message);
    }
}