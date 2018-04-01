package bharat.sos_tarp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import bharat.sos_tarp.MainActivity;
import bharat.sos_tarp.R;

import java.util.Date;

/**
 * Created by koteswarao
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
                    alertButton.setVisibility(View.VISIBLE);
                    completeTextView.setVisibility(View.VISIBLE);
                    count += 1;
                }
            }
        });
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = completeTextView.getText().toString();
                res = res.isEmpty() ? "Critical Emergency" + getSpaces() : res + getSpaces();
                ((MainActivity) getActivity()).sendAlertMessage(res);
            }
        });

    }

    private String getSpaces() {
        String space = "";
        int s = (new Date()).getSeconds() % 10;
        for (int i = 0; i < s; i++) {
            space += " ";
        }
        return space;
    }

    public void backPressed() {
        if (count > 0) {
            count = 0;
            emergencyButton.animate().translationYBy(300).setDuration(400);
            layout.animate().alpha(0).setDuration(400);
            alertButton.setVisibility(View.GONE);
            completeTextView.setVisibility(View.GONE);
        }
    }
}
