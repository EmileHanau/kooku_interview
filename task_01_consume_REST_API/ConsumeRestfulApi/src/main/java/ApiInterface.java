import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApiInterface {
    private final String company_id;
    private final String company_name;

    ApiInterface(String company_name, String company_id) {
        this.company_id = company_id;
        this.company_name = company_name;
    }

    public List<Candidate>retrieveCandidatesByOfferID(String offer_id) {
        try {
            URL url = new URL("https://api.recruitee.com/c/" + company_id + "/candidates" + "?offer_id=" + offer_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                System.err.println("Failed : HTTP error code : " + conn.getResponseCode() + "\nUsing example backup-data instead!");
                return buildCandidatesFromJSON(new JSONObject(example_data_candidates));
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            StringBuilder response_plain = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null)
                response_plain.append(output);
            conn.disconnect();
            return buildCandidatesFromJSON(new JSONObject(response_plain.toString()));
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
    }

    private List<Candidate>buildCandidatesFromJSON(JSONObject json) {
        List<Candidate> res = new ArrayList<Candidate>();
        try {
            JSONArray candidates_json = json.getJSONArray("candidates");;
            for (int i = 0; i < candidates_json.length(); i++)
                res.add(new Candidate(candidates_json.getJSONObject(i)));
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
        return res;
    }

    private final String example_data_candidates = """
            {
               "candidates": [
                 {
                   "admin_id": null,
                   "adminapp_url": "http://app.recruitee.local:3003/#/dashboard/overview?candidate=1630&company=2772",
                   "created_at": "2021-04-09T15:42:02.784610Z",
                   "emails": [],
                   "example": false,
                   "followed": false,
                   "id": 1630,
                   "last_message_at": null,
                   "my_last_rating": null,
                   "my_pending_result_request": false,
                   "my_upcoming_event": false,
                   "name": "John Doe",
                   "notes_count": 0,
                   "pending_result_request": false,
                   "phones": [],
                   "photo_thumb_url": null,
                   "placements": [
                     {
                       "candidate_id": 1630,
                       "created_at": "2021-04-09T15:42:02.787948Z",
                       "id": 758,
                       "language": null,
                       "offer_id": 1515,
                       "overdue_at": null,
                       "overdue_diff": null,
                       "position": 49,
                       "stage_id": null,
                       "updated_at": "2021-04-09T15:42:02.787948Z"
                     }
                   ],
                   "positive_ratings": null,
                   "rating": 0,
                   "ratings": {},
                   "ratings_count": 0,
                   "referrer": null,
                   "source": null,
                   "tasks_count": 0,
                   "unread_notifications": false,
                   "upcoming_event": false,
                   "updated_at": "2021-04-09T15:42:02.784610Z",
                   "viewed": false
                 },
                 {
                   "admin_id": null,
                   "adminapp_url": "http://app.recruitee.local:3003/#/dashboard/overview?candidate=1629&company=2772",
                   "created_at": "2021-04-09T15:42:02.776600Z",
                   "emails": [],
                   "example": false,
                   "followed": false,
                   "id": 1629,
                   "last_message_at": null,
                   "my_last_rating": null,
                   "my_pending_result_request": false,
                   "my_upcoming_event": false,
                   "name": "Max Mustermann",
                   "notes_count": 0,
                   "pending_result_request": false,
                   "phones": [],
                   "photo_thumb_url": null,
                   "placements": [
                     {
                       "candidate_id": 1629,
                       "created_at": "2021-04-09T15:42:02.779387Z",
                       "id": 757,
                       "language": null,
                       "offer_id": 1515,
                       "overdue_at": null,
                       "overdue_diff": null,
                       "position": 48,
                       "stage_id": null,
                       "updated_at": "2021-04-09T15:42:02.779387Z"
                     },
                                          {
                       "candidate_id": 1629,
                       "created_at": "2021-04-09T15:42:02.779387Z",
                       "id": 758,
                       "language": null,
                       "offer_id": 1515,
                       "overdue_at": null,
                       "overdue_diff": null,
                       "position": 48,
                       "stage_id": null,
                       "updated_at": "2021-04-09T15:42:02.779387Z"
                     },
                                          {
                       "candidate_id": 1629,
                       "created_at": "2021-04-09T15:42:02.779387Z",
                       "id": 759,
                       "language": null,
                       "offer_id": 1515,
                       "overdue_at": null,
                       "overdue_diff": null,
                       "position": 48,
                       "stage_id": null,
                       "updated_at": "2021-04-09T15:42:02.779387Z"
                     },
                                          {
                       "candidate_id": 1629,
                       "created_at": "2021-04-09T15:42:02.779387Z",
                       "id": 760,
                       "language": null,
                       "offer_id": 1515,
                       "overdue_at": null,
                       "overdue_diff": null,
                       "position": 48,
                       "stage_id": null,
                       "updated_at": "2021-04-09T15:42:02.779387Z"
                     }
                   ],
                   "positive_ratings": null,
                   "rating": 0,
                   "ratings": {},
                   "ratings_count": 0,
                   "referrer": null,
                   "source": null,
                   "tasks_count": 0,
                   "unread_notifications": true,
                   "upcoming_event": false,
                   "updated_at": "2021-04-09T15:42:02.776600Z",
                   "viewed": false
                 }
               ],
               "generated_at": "2021-04-09T15:42:02.793700Z",
               "references": [
                 {
                   "id": 1515,
                   "kind": "job",
                   "lang_code": "en",
                   "location": "Poznań, Poland",
                   "position": 677,
                   "slug": "best-offer676",
                   "status": "draft",
                   "title": "Best Offer",
                   "type": "Offer"
                 }
               ]
             }""";
}
