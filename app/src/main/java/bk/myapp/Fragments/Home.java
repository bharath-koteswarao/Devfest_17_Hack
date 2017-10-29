package bk.myapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import bk.myapp.MainActivity;
import bk.myapp.R;

/**
 * Created by koteswarao on 29-10-2017.
 * ${CLASS}
 */

public class Home extends Fragment {
    View v;
    ImageView emergencyButton;
    AutoCompleteTextView completeTextView;
    Button alertButton;
    LinearLayout layout;
    int count = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_frag, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] reasons = new String[]{"Heart attack", "Crime", "Accident"
            , "Natural disasters", "Chemical Emergency", "Earth quake",
            "Fire", "Flood", "Heat wave", "Sunstroke", "Hurricane", "Landslide", "Poisoning",
            "Terrorism", "Tornado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, reasons);
        emergencyButton = (ImageView) getActivity().findViewById(R.id.emergency_button);
        completeTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.type);
        alertButton = (Button) getActivity().findViewById(R.id.alertButton);
        layout = (LinearLayout) getActivity().findViewById(R.id.ll);
        completeTextView.setAdapter(adapter);
        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    emergencyButton.animate().translationYBy(-300).setDuration(400);
                    layout.animate().alpha(1).setDuration(400);
                    count += 1;
                }
            }
        });
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).sendAlertMessage(completeTextView.getText().toString());
            }
        });

    }

    public void backPressed() {
        if (count > 0) {
            count = 0;
            emergencyButton.animate().translationYBy(300).setDuration(400);
            layout.animate().alpha(0).setDuration(400);
        }
    }
}
