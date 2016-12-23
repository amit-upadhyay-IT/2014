package com.amitupadhyay.ait.approach;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amitupadhyay.ait.flowingdrawer_core.LeftDrawerLayout;
import com.amitupadhyay.ait.flowingdrawer_core.MenuFragment;
import com.squareup.picasso.Picasso;



public class MyMenuFragment extends MenuFragment {

    private ImageView ivMenuUserProfilePhoto;
    public NavigationView navigationView;
    private LeftDrawerLayout mLeftDrawerLayout;
    AmitCallbackWhenDrawerOpens amitCallbackWhenDrawerOpens;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        amitCallbackWhenDrawerOpens = (AmitCallbackWhenDrawerOpens) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);

        View view1 = inflater.inflate(R.layout.activity_main, container,
                false);
        mLeftDrawerLayout = (LeftDrawerLayout) view1.findViewById(R.id.id_drawerlayout);

        navigationView = (NavigationView) view.findViewById(R.id.vNavigation);

        //View navHeader = navigationView.getHeaderView(0); using when we are using support library greater than 23.0.0
        ivMenuUserProfilePhoto = (ImageView) navigationView.findViewById(R.id.ivMenuUserProfilePhoto);
        setupHeader();

        return  setupReveal(view) ;
    }

    private void setupHeader() {
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
        String profilePhoto = getResources().getString(R.string.user_profile_photo);
        Picasso.with(getActivity())
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(ivMenuUserProfilePhoto);
    }

    public void onOpenMenu(){
        if (amitCallbackWhenDrawerOpens!= null)
            amitCallbackWhenDrawerOpens.drawerIsOpen();
    }
    public void onCloseMenu(){
    }

    public interface AmitCallbackWhenDrawerOpens {
        void drawerIsOpen();
    }
}
