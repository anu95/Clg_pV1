package com.example.dell.mobility_v1;


import android.content.Context;
import android.content.Intent;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import android.os.Build;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.dell.mobility_v1.recycler.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import com.example.dell.mobility_v1.util.OnSwipeTouchListener;
import com.example.dell.mobility_v1.util.RecyclerTouchListener;

public class Workspace extends AppCompatActivity implements  TaskListener{
    Main m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ViewPager Pager = (ViewPager) findViewById(R.id.container_main);


         m=new Main(this,this.getSupportFragmentManager(),Pager);
       
        LinearLayout l=(LinearLayout)findViewById(R.id.w_space);
     final   Intent in=new Intent(this,Briefing.class);
        l.setOnTouchListener(new OnSwipeTouchListener(this) {

            @Override
            public void onSwipeRight() {

              goBack(getApplicationContext());

            }

            @Override
            public void onSwipeLeft() {

//                startActivity(in);
//                overridePendingTransition(R.anim.exit_1, R.anim.exit_2);
                gotoDeploy(getApplicationContext());

            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
boolean second_set_is_visible=false;
public boolean setTransition(boolean gonext){
    boolean done=false;




    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        ViewGroup mSceneRoot = (ViewGroup) findViewById(R.id.go_tos);
        Transition mFadeTransition = new Slide();
        Scene mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.screenbuttons, this);
        Scene mEndingScene =
                Scene.getSceneForLayout(mSceneRoot, R.layout.screen_buttons_prev, this);
       if(gonext) {TransitionManager.go(mEndingScene, mFadeTransition);}
        else TransitionManager.go(mAScene, mFadeTransition);;
        done=true;
        return done;
     }
    else {
        ViewGroup mSceneRoot = (ViewGroup) findViewById(R.id.go_tos);

        //do something for pre kitkat versions
        return done;}

    }
    public void fadeOutanim(final View img)
    {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(120);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                img.setVisibility(View.GONE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        img.startAnimation(fadeOut);
    }
    public boolean travTransition(boolean travel){
        boolean done=false;
        ViewGroup mSlidingView = (ViewGroup) findViewById(R.id.res);
        if(travel) {

            mSlidingView.setVisibility(View.VISIBLE);
//            ObjectAnimator mSlidInAnimator = ObjectAnimator.ofFloat(mSlidingView, "translationX", 0);
//            mSlidInAnimator.setDuration(300);
//            mSlidInAnimator.start();
            Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                    android.R.anim.slide_in_left);
            mSlidingView.startAnimation(animFadein);
        }
        else
        {
            Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_out_en);
            mSlidingView.startAnimation(animFadein);
            mSlidingView.setVisibility(View.GONE);

        }
         return done;



    }

    public void goprev(View v)
    {
        setTransition(false);
    }
    public void gonext(View v)
    {
        setTransition(true);
    }
    public void goToDeploy(View v)
    {
       gotoDeploy(this);

    }
 void  gotoDeploy(Context c)
{
    Intent i=new Intent(c,Deploy.class);

    startActivity(i);
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out_en);
}
    public void gotonew(View v)
    {
        ViewPager  mViewPager = (ViewPager) findViewById(R.id.container_main);
        mViewPager.setCurrentItem(3, true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            // ...

            // up button
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out_en);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void travel(View v)
    {
      ToggleButton  vv = (ToggleButton) v;
//        travTransition();
        if(vv.isChecked()){
//            Toast.makeText(this,"checked",Toast.LENGTH_SHORT).show();
           travTransition(true);
        }
        else{
            travTransition(false);
        }
    }


public void populate (char a)
{
    int id=R.id.item_acc_text;
    String[] acc={"ch@","e@","g@"};

    ArrayAdapter adapter = new ArrayAdapter<String>(this,   R.layout.acc_item, R.id.item_acc_text, acc);
    ListView lv= (ListView)findViewById(R.id.listView);
    switch(a)
{
    case 'a':
      lv.setAdapter(adapter);
        break;
    case 'c':
        acc[0]="A";
        acc[1]="B";
        acc[2]="C";
        adapter = new ArrayAdapter<String>(this,   R.layout.contacts_items, R.id.name, acc);
        lv.setAdapter(adapter);
        break;

}
}

    public void GoTo(int layout,ViewGroup root,int curr_id){
        boolean done=false;




        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup mSceneRoot = root;

            Transition enter= TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.slide_left);


            Transition exit= TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.slide_right);
            Log.d("Durr",exit.getDuration()+"ms");
           exit.setDuration(50);
            Log.d("Durr", exit.getDuration() + "ms");
//              mFadeTransition.setSlideEdge(Gravity.START);      ;
//            Scene mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.screenbuttons, this);
            Scene end =
                    Scene.getSceneForLayout(mSceneRoot,layout, this);
            Scene start =Scene.getSceneForLayout(mSceneRoot,R.layout.contacts,this);
//            TransitionManag=er.go(start, exit);
//bool
            ViewGroup vg= (ViewGroup) root.getChildAt(0);
            TransitionSet t= new TransitionSet();
            exit.addTarget(vg);
            t.addTransition(exit);
            enter.addTarget(curr_id);
            t.addTransition(enter);

            boolean b=vg.getId()==curr_id&&curr_id!=R.id.tab_layout;
            Log.d("My result","RES= "+b);
            if(!b)
            {

                TransitionManager.go(end, t);
            }
//            TransitionManager m=new TransitionManager();
//
//            m.setTransition(start,end,enter);

                    ;


        }
        else {
            ViewGroup mSceneRoot = root;

            //do something for pre kitkat versions
            }

    }

 void goBack(Context c)
{
    Intent i=new Intent(c,Briefing.class);

    startActivity(i);
    overridePendingTransition(R.anim.exit_1, R.anim.exit_2);
}

    @Override public boolean onKeyDown(int ni, KeyEvent event) {

        // only intercept back button press
        if (ni == KeyEvent.KEYCODE_BACK) {

goBack(this);
        }

        return false; // propagate this keyevent
    }





    public void goToScreen(View v)
    {
        ViewGroup root = (ViewGroup) findViewById(R.id.pmd_placeholder);
        ViewPager  Pager = (ViewPager) findViewById(R.id.container_main);
//        Scene mAScene = Scene.getSceneForLayout(root, ()findViewById(R.id.curr_scr), this);
       switch( v.getId())
       {
           case R.id.goacc :

//               GoTo(R.layout.acc,root,R.id.acc_layout);
//               populate('a');
//               m.cf.refresh();
               m.getViewPager().setCurrentItem(0, true);
               break;
           case R.id.go_cn :
               m.getViewPager().setCurrentItem(1, true);
//               GoTo(R.layout.contacts, root, R.id.contact_layout);
//        contacts_done=  ContactsRecycle();
//               new ContactTask().execute();
//               android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//               transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
//               transaction.add(R.id.pmd_placeholder, new ContactsFragment()).commit();


               break;
           case R.id.go_mul:
//           m.cf.explodeDetail_4close();
               m.getViewPager().setCurrentItem(2, true);
//               GoTo(R.layout.tabview,root,R.id.tab_layout);
//               ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
//
//               TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsn);
//               int [] icons={R.drawable.pic,R.drawable.music,R.drawable.vid,R.drawable.file};
//               Tab_List t=new Tab_List(this,this.getSupportFragmentManager(),mViewPager,tabLayout,icons,(RecyclerView)findViewById(R.id.recycle_list));
               break;

           case R.id.go_app:
//               GoTo(R.layout.tabview_app,root,R.id.tab_layout_app);
//                mViewPager = (ViewPager) findViewById(R.id.container_app);
//
//                tabLayout = (TabLayout) findViewById(R.id.tabsn_app);
//
//               int [] icons_app={R.drawable.default_app,R.drawable.playstore,R.drawable.final_list};
//               Tab_List_App t2=new Tab_List_App(this,this.getSupportFragmentManager(),mViewPager,tabLayout,icons_app);
//               m.cf.refresh();
               m.getViewPager().setCurrentItem(3, true);
               break;

           case R.id.devicesettings:
//               GoTo(R.layout.device_setting,root,R.id.device_setting_layout);
//               m.cf.refresh();
//               m.cf.refresh();
               m.getViewPager().setCurrentItem(4, true);
               break;

           case R.id.desktopsettings:
//               GoTo(R.layout.desktop_setting, root, R.id.desktop_settings_layout);
//               initRecycle();
//               m.cf.refresh();
               m.getViewPager().setCurrentItem(5,true);
               break;
       }
    }
    public void push()
    {
        try {

            Log.d("PUSH ", "Tag 0 :" + this.getSupportFragmentManager().getFragments().get(2));
        } catch (Exception e) {
            Log.d("PUSH-EXC ", "Tag 0 :" + e.getMessage() +e.getCause());
        }
    }
    public void expandContactSearch()
    {
        m.cf.expandContactSearch();

//        ViewGroup mSceneRoot = (ViewGroup)findViewById(R.id.cont_change);
//
////              mFadeTransition.setSlideEdge(Gravity.START);      ;
////            Scene mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.screenbuttons, this);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            Transition exit= TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_left);
//            Transition enter=  TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_right);
//
//            TransitionSet t= new TransitionSet();
//            exit.addTarget(R.id.search_stage_A);
//            t.addTransition(exit);
//            enter.addTarget(R.id.search_stage_B);
//            t.addTransition(enter);
//            Scene end =
//                    Scene.getSceneForLayout(mSceneRoot,R.layout.contactheader_expanded, this);
//            TransitionManager.go(end,t);
//            textwatch();

//        }



    }
    public void contactTogg(View v)
    {

            expandContactSearch();

    }
    public void closeContactSearch()
    {

//        ViewGroup mSceneRoot = (ViewGroup)findViewById(R.id.cont_change);
//
////              mFadeTransition.setSlideEdge(Gravity.START);      ;
////            Scene mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.screenbuttons, this);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            Transition exit= TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_left);
//
//            Scene end =
//                    Scene.getSceneForLayout(mSceneRoot,R.layout.contactheader_1, this);
//            TransitionManager.go(end, exit);
//        }
//       contacts_done=ContactsRecycle();

    }
    public void closeContactSearch_click(View v)
    {
m.cf.closeContactSearch_click();
//        ViewGroup mSceneRoot = (ViewGroup)findViewById(R.id.cont_change);
//
////              mFadeTransition.setSlideEdge(Gravity.START);      ;
////            Scene mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.screenbuttons, this);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            Transition exit= TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_left);
//            Transition enter=  TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_right);
//
//            TransitionSet t= new TransitionSet();
//            exit.addTarget(R.id.search_stage_A);
//            t.addTransition(exit);
//            enter.addTarget(R.id.search_stage_B);
//            t.addTransition(enter);
//            Scene end =
//                    Scene.getSceneForLayout(mSceneRoot,R.layout.contactheader_1, this);
//            TransitionManager.go(end, t);
//          contacts_done= ContactsRecycle();
//        }


    }
void progress_contacts(boolean show)
{

//    LinearLayout l=(LinearLayout)findViewById(R.id.contacts_recycle_container);
//    ProgressBar p=(ProgressBar)findViewById(R.id.p_contacts);
//    if(show)
//    {
//        p.setVisibility(View.VISIBLE);
//        l.setVisibility(View.GONE);
//    }
//    else
//    {
//        p.setVisibility(View.GONE);
//        l.setVisibility(View.VISIBLE);
//    }
}

    @Override
    public void onTaskStarted() {

    }

    @Override
    public void onTaskFinished(String result) {

    }

//    private class ContactTask extends AsyncTask<Void, Integer, Void>
//    {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////           progress_contacts(true);
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
////            updateProgressBar(values[0]);
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
////          if(!contacts_done)contacts_done=  ContactsRecycle();
////            prepareContacts();
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
////            progress_contacts(false);
//            cA.notifyDataSetChanged();
//
//        }
//
//
//
//    }
//

   public void explodeDetail(View v)
    {
switch(v.getId())
{
    case R.id.contact_layout_pic:
//        m.cf.explodeDetail();
        break;
}
//        ViewGroup mSceneRoot = (ViewGroup) findViewById(
//                R.id.contacts_recycle_container
//        );
////                ;
////        Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),
////               android.R.anim.fade_out);
//         Button tobhidden =(Button)findViewById(R.id.search_my_contacts);
//        tobhidden.setVisibility(View.INVISIBLE);
////              mFadeTransition.setSlideEdge(Gravity.START);      ;
////            Scene mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.screenbuttons, this);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//
//            Transition ex=  TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_left);
//ex.setDuration(170);
//            Transition changeTransform = TransitionInflater.from(this).
//                    inflateTransition(R.transition.change);
//            changeTransform.addTarget(v.getId());
//            Transition t2=  TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_right);
//            t2.addTarget(R.id.recycle_list);
////changeTransform.addTarget();
//            TransitionSet ts=new TransitionSet();
//            t2.setDuration(260);
////            ts.addTransition(changeTransform);
//            ts.addTransition(t2);
//            ts.addTransition(ex);
//            Scene end =
//                    Scene.getSceneForLayout(mSceneRoot,R.layout.resource_detail, this);
//            TransitionManager.go(end, ex);
//
////            this.getSupportFragmentManager().beginTransaction()
////                    .replace(R.id.contacts_recycle_container, fragmentTwo)
////                    .addToBackStack("transaction")
////                    .addSharedElement(ivProfile, "profile");
//
////            textwatch();
//
//        }



    }

    public void closeDetail(View v){



                m.cf.explodeDetail_4close();


//        ViewGroup mSceneRoot = (ViewGroup) findViewById(
//                R.id.contacts_recycle_container
//        );
////                ;
//
//
////        Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),
////               android.R.anim.fade_out);
//        Button tobhidden =(Button)findViewById(R.id.search_my_contacts);
//        tobhidden.setVisibility(View.VISIBLE);
////              mFadeTransition.setSlideEdge(Gravity.START);      ;
////            Scene mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.screenbuttons, this);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//
//            Transition ex=  TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_left);
//            ex.setDuration(200);
//            Transition t2=  TransitionInflater.from(this)
//                    .inflateTransition(android.R.transition.slide_right);
//            t2.setDuration(200);
//            TransitionSet ts=new TransitionSet();
//          t2.addTarget(R.id.resource_detail);
//            ts.addTransition(t2);
//            ts.addTransition(ex);
//            Scene end =
//                    Scene.getSceneForLayout(mSceneRoot,R.layout.recycle_list, this);
//            TransitionManager.go(end, ex);
//
//            ContactsRecycle();
//
////            textwatch();
//
//        }



    }
//    public  void textclick(View v)
//    {
//        Log.d("click","Textclick.........................................");
//        textwatch();
//    }
    List<Test_Item> testItemList;
    RecyclerView recyclerView;
    GamesAdapter mAdapter;
//    ContactAdapterR cA;List<Contact> cl;
//    private void textwatch()
//    {
//        final EditText tv_filter = (EditText) findViewById(R.id.cont_search);
//      if(!contacts_done)ContactsRecycle();
//        TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
//            String[] list={"kaur","mundi","delnaz","darshini","keval","pulkit","lehri","lehti"};
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Contacts_Recycle_Search();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//              search.clear();
////                search =new ArrayList<>();
//                if (LongEnough()) {
////                    populateList();
//                    Log.d("Search","Searching.....................................................................................+  "+cl.size());
//                    for (int y=0;y<cl.size();y++){
//
//                        if(cl.get(y).getName().toLowerCase().contains(s)||cl.get(y).getName().contains(s))
//                        {Log.d("found",s+":"+cl.get(y).getName());
//                        AddContact_Search(cl.get(y));
//
//                        }
//
//
//                    }
//                }
//            }
//
//            private boolean LongEnough() {
//                return tv_filter.getText().toString().trim().length() > 2;
//            }
//        };
//        tv_filter.addTextChangedListener(fieldValidatorTextWatcher);
//    }
//
//
//
//
//    private void AddContact_Search(Contact s) {
//boolean add=true;
//         for(Contact i : search)
//         {
//             if(i.getName().equals(s.getName())) add=false;
//         }
//
//        if(add){search.add(s);Log.d("new","go");}
//        else Log.d("Not","Added");
//
//
//        cA.notifyDataSetChanged();
//    }

    private boolean initRecycle()
{
    try{
        testItemList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter=  new GamesAdapter(testItemList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                Test_Item movie = testItemList.get(position);
                Toast.makeText(getApplicationContext(), movie.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }
        }));
        prepareGameData();
        return true;
    }
    catch(Exception e)
    {
        return false;
    }
}

//    private boolean Contacts_Recycle_Search()
//    {
//        try{
//            search = new ArrayList<>();
//            recyclerView = (RecyclerView) findViewById(R.id.recycle_list);
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//
//            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            cA=  new ContactAdapterR(search);
//            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//
//            recyclerView.setAdapter(cA);
//            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
//                @Override
//                public void onClick(View view, int position) {
//
//                }
//
//                @Override
//                public void onLongClick(View view, int position) {
////                    Contact c = cl.get(position);
////                    Toast.makeText(getApplicationContext(), c.getName() + " is selected!", Toast.LENGTH_SHORT).show();
//                }
//            }));
//
//            return true;
//        }
//        catch(Exception e)
//        {
//            return false;
//        }
//    }
//    private  boolean contacts_done=false;
//    private boolean ContactsRecycle()
//    {
//        try{
//
//          if(!contacts_done) cl = new ArrayList<>();
//            recyclerView = (RecyclerView) findViewById(R.id.recycle_list);
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//
//            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            cA=  new ContactAdapterR(cl);
//            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//
//            recyclerView.setAdapter(cA);
//            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
//                @Override
//                public void onClick(View view, int position) {
//
//                }
//
//                @Override
//                public void onLongClick(View view, int position) {
//                    Contact c = cl.get(position);
//                    Toast.makeText(getApplicationContext(), c.getName() + " is selected!", Toast.LENGTH_SHORT).show();
//                }
//            }));
////            prepareContacts();
//            new ContactTask().execute();
//
//            return true;
//        }
//        catch(Exception e)
//        {
//            return false;
//        }
//    }
//List<Contact> search;

    private void lockScreenOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void unlockScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }
    private void prepareGameData() {



//       Test_Item testItem = new Test_Item("Mad Max: Fury Road", "Action & Adventure", "2015");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("Inside Out", "Animation, Kids & Family", "2015");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("Shaun the Sheep", "Animation", "2015");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("The Martian", "Science Fiction & Fantasy", "2015");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("Mission: Impossible Rogue Nation", "Action", "2015");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("Up", "Animation", "2009");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("Star Trek", "Science Fiction", "2009");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("The LEGO Movie", "Animation", "2014");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("Iron Man", "Action & Adventure", "2008");
//        testItemList.add(testItem);
//
//        testItem = new Test_Item("Aliens", "Science Fiction", "1986");
//        testItemList.add(testItem);



        mAdapter.notifyDataSetChanged();
    }

//    private void emptyContacts() {
//
//        cl.removeAll(cl);
//    }

//    private void prepareContacts() {
//
//
//        if (cl.size()==0) {
//            Contact c=new Contact("Shafiq","909890");
//            cl.add(c);
//            c=new Contact("Shasak","90977899");
//            cl.add(c);
//            c=new Contact("Shami","90977899");
//            cl.add(c);
//            c=new Contact("Sharif","90977899");
//            cl.add(c);
//            c=new Contact("Shaan","90977899");
//            cl.add(c);
//            c=new Contact("Sharjil","90977899");
//            cl.add(c);
//            c=new Contact("Shenwari","90977899");
//            cl.add(c);
//            c=new Contact("Shashi","90977899");
//            cl.add(c);
//            c=new Contact("Shasak","90977899");
//            cl.add(c);
//            c=new Contact("Saminuahh","90977899");
//            cl.add(c);
//
////            cA.notifyDataSetChanged();
//        } else {
//        }
//    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


}
