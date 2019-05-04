package quick_union;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/5/4 15:34
 * @Version 1.0
 */
public interface UF {
    boolean isConnected(int p,int q);
    void unionElements(int p,int q);
    int getSize();
}
