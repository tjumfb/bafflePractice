import org.junit.Test;

class MathHelper {
    public int add(int a, int b) {
        return a + b;
    }
    @Test
    public void testMultiply() {
        MathHelper mathHelper = mock(MathHelper.class);
        for (int i = 0; i < 10; i++) {
            when(mathHelper.add(i * 8, 8)).thenReturn(i * 8 + 8);
        }
        MathUtil mathUtil = new MathUtil(mathHelper);
        mathUtil.multiply(3, 8);
        verify(mathHelper, times(1)).add(0, 8);
        verify(mathHelper, times(1)).add(8, 8);
        verify(mathHelper, times(1)).add(16, 8);
    }
}

public class TestMock {
}
