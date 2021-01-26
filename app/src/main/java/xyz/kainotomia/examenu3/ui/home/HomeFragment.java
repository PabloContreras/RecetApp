package xyz.kainotomia.examenu3.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import xyz.kainotomia.examenu3.R;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /*VideoView video = root.findViewById(R.id.videoView);

        video.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.video));
        MediaController mediaController = new MediaController(this.getContext());
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);*/

        return root;
    }
}