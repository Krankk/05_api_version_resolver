package ru.neoflex.dev.spring.api_version_resolver.vMapping;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import static ru.neoflex.dev.spring.api_version_resolver.Constants.*;

import javax.servlet.http.HttpServletRequest;

public class VersionRequestCondition implements RequestCondition<VersionRequestCondition> {

    private String version;

    public VersionRequestCondition(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }


    @Override
    public VersionRequestCondition combine(VersionRequestCondition other) {
        return null;
    }

    @Override /* This method will try to check THIS condition suffice for incoming http request */
    public VersionRequestCondition getMatchingCondition(HttpServletRequest other) {
        String otherVersion = other.getHeader(VERSION_HEADER);
        if (!StringUtils.isEmpty(otherVersion) && otherVersion.equals(version)) {
            return this;
        } else
            return null;
    }

    @Override
    public int compareTo(VersionRequestCondition other, HttpServletRequest request) {
        String otherVer = other.getVersion();
        String currentVer = request.getHeader(VERSION_HEADER);

        if (currentVer.equals(V_2) && otherVer.equals(V_1))
            return 1;
        else if (currentVer.equals(V_1) && otherVer.equals(V_2))
            return -1;
        else
            return 0;
    }
}