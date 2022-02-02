package ru.stqa.project1.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("truelala58", "java_project1"))
                .commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
