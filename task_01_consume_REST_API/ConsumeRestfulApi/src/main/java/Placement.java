import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

public class Placement {
    public int id;
    public int stage_id;
    public int position;
    public int offer_id;
    public Instant updated_at;
    public Instant created_at;

    Placement(JSONObject json) throws JSONException {
        id = json.optInt("id");
        stage_id = json.optInt("stage_id");
        position = json.optInt("position");
        offer_id = json.optInt("offer_id");
        updated_at = Instant.parse(json.optString("updated_at").toString());
        created_at = Instant.parse(json.optString("created_at").toString());
    }
}
