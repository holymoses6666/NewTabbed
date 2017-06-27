package com.rodion.newtabbed;



        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.PagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v4.view.ViewPager.OnPageChangeListener;
        import android.util.Log;

public class MainActivity extends FragmentActivity {

    static final String TAG = "myLogs";
    static  int PAGE_COUNT = 10; //количество страниц

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());//создаем адаптер
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);//номер текущей отображенной страницы
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,//значение скроллера при пролистывании
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {//сообщает нам о состоянии, в котором находится скроллер (SCROLL_STATE_IDLE – ничего не скролится, SCROLL_STATE_DRAGGING – пользователь «тащит» страницу, SCROLL_STATE_SETTLING – скроллер долистывает страницу до конца)
            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);//по номеру страницы нам надо вернуть фрагмент, используем наш метод newInstance
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;         // здесь мы должны возвращать кол-во страниц, используем константу
        }

    }

}
