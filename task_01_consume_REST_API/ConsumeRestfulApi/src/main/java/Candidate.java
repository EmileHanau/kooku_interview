import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Candidate {
    public int id;
    public String name;
    public List<String> emails = new ArrayList<String>();
    public List<Placement> placements = new ArrayList<Placement>();
    public String cv_url = "";
    // incomplete; response has far more fields

    Candidate(JSONObject json) throws JSONException {
        id = json.optInt("id");
        name = json.optString("name");
        cv_url = json.optString("cv_url");
        cv_url = "https://docs.google.com/uc?export=download&id=1XXySusprdAq90qJa42SFt7RviMpN8425"; // debug
        JSONArray json_emails = json.getJSONArray("emails");
        for (int i = 0; i < json_emails.length(); i++)
            emails.add(json_emails.get(i).toString());
        JSONArray json_placements = json.getJSONArray("placements");
        for (int i = 0; i < json_placements.length(); i++)
            placements.add(new Placement(new JSONObject(json_placements.get(i).toString())));
    }

    public void downloadCV() {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(cv_url));
            }
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
