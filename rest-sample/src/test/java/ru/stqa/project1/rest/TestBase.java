package ru.stqa.project1.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        if ("Resolved".equals(getIssueStatus(issueId)) || ("Closed".equals(getIssueStatus(issueId)))) {
            return false;
        }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public String getIssueStatus(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issueParams = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        return issueParams.iterator().next().getStateName();
    }

}