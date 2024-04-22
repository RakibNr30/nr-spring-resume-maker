package com.resume.route;

public final class WebRoutes {

    /*
    * FRONT ROUTES START
    */
    public static final String FRONT_PREFIX = "";
    public static final String HOME         = FRONT_PREFIX + "/";
    public static final String ABOUT        = FRONT_PREFIX + "/about";
    public static final String USER         = FRONT_PREFIX + "/user";
    public static final String CONTACT      = FRONT_PREFIX + "/contact";
    /*
     * FRONT ROUTES END
     */

    /*
    * DASHBOARD ROUTES START
    */
    public static final String DASHBOARD_PREFIX                     = "/dashboard";
    public static final String DASHBOARD                            = DASHBOARD_PREFIX;


    /* PROFILE ROUTES START */
    public static final String PROFILE_PREFIX                       = DASHBOARD_PREFIX + "/profile";

    /* PROFILE ACCOUNT ROUTES */
    public static final String PROFILE_ACCOUNT                      = PROFILE_PREFIX;
    public static final String PROFILE_ACCOUNT_UPDATE               = PROFILE_PREFIX + "/update";

    /* PROFILE EDUCATION ROUTES */
    public static final String PROFILE_EDUCATION                    = PROFILE_PREFIX + "/education";
    public static final String PROFILE_EDUCATION_STORE              = PROFILE_PREFIX + "/education/store";
    public static final String PROFILE_EDUCATION_UPDATE             = PROFILE_PREFIX + "/education/{id}/update";
    public static final String PROFILE_EDUCATION_DESTROY            = PROFILE_PREFIX + "/education/{id}/destroy";

    /* PROFILE EXPERIENCE ROUTES */
    public static final String PROFILE_EXPERIENCE                   = PROFILE_PREFIX + "/experience";
    public static final String PROFILE_EXPERIENCE_STORE             = PROFILE_PREFIX + "/experience/store";
    public static final String PROFILE_EXPERIENCE_UPDATE            = PROFILE_PREFIX + "/experience/{id}/update";
    public static final String PROFILE_EXPERIENCE_DESTROY           = PROFILE_PREFIX + "/experience/{id}/destroy";

    /* PROFILE AWARD ROUTES */
    public static final String PROFILE_AWARD                        = PROFILE_PREFIX + "/award";
    public static final String PROFILE_AWARD_STORE                  = PROFILE_PREFIX + "/award/store";
    public static final String PROFILE_AWARD_UPDATE                 = PROFILE_PREFIX + "/award/{id}/update";
    public static final String PROFILE_AWARD_DESTROY                = PROFILE_PREFIX + "/award/{id}/destroy";

    /* PROFILE SKILL ROUTES */
    public static final String PROFILE_SKILL                        = PROFILE_PREFIX + "/skill";
    public static final String PROFILE_SKILL_STORE                  = PROFILE_PREFIX + "/skill/store";
    public static final String PROFILE_SKILL_UPDATE                 = PROFILE_PREFIX + "/skill/{id}/update";
    public static final String PROFILE_SKILL_DESTROY                = PROFILE_PREFIX + "/skill/{id}/destroy";

    /* PROFILE INTEREST ROUTES */
    public static final String PROFILE_INTEREST                     = PROFILE_PREFIX + "/interest";
    public static final String PROFILE_INTEREST_STORE               = PROFILE_PREFIX + "/interest/store";
    public static final String PROFILE_INTEREST_UPDATE              = PROFILE_PREFIX + "/interest/{id}/update";
    public static final String PROFILE_INTEREST_DESTROY             = PROFILE_PREFIX + "/interest/{id}/destroy";

    /* PROFILE SOCIAL ROUTES */
    public static final String PROFILE_SOCIAL                       = PROFILE_PREFIX + "/social";
    public static final String PROFILE_SOCIAL_STORE                 = PROFILE_PREFIX + "/social/store";
    public static final String PROFILE_SOCIAL_UPDATE                = PROFILE_PREFIX + "/social/{id}/update";
    public static final String PROFILE_SOCIAL_DESTROY               = PROFILE_PREFIX + "/social/{id}/destroy";

    /* PROFILE LANGUAGE ROUTES */
    public static final String PROFILE_LANGUAGE                     = PROFILE_PREFIX + "/language";
    public static final String PROFILE_LANGUAGE_STORE               = PROFILE_PREFIX + "/language/store";
    public static final String PROFILE_LANGUAGE_UPDATE              = PROFILE_PREFIX + "/language/{id}/update";
    public static final String PROFILE_LANGUAGE_DESTROY             = PROFILE_PREFIX + "/language/{id}/destroy";
    public static final String PROFILE_PHOTO_STORE                  = PROFILE_PREFIX + "/profile-photo/update";
    /* PROFILE ROUTES END */


    /* UMS ROUTES START */
    public static final String UMS_PREFIX                           = DASHBOARD_PREFIX + "/ums";

    /* UMS ROLE ROUTES */
    public static final String UMS_ROLE                             = UMS_PREFIX + "/role";
    public static final String UMS_ROLE_CREATE                      = UMS_PREFIX + "/role/create";
    public static final String UMS_ROLE_STORE                       = UMS_PREFIX + "/role/store";
    public static final String UMS_ROLE_SHOW                        = UMS_PREFIX + "/role/{id}";
    public static final String UMS_ROLE_EDIT                        = UMS_PREFIX + "/role/{id}/edit";
    public static final String UMS_ROLE_UPDATE                      = UMS_PREFIX + "/role/{id}/update";
    public static final String UMS_ROLE_DESTROY                     = UMS_PREFIX + "/role/{id}/destroy";

    /* UMS USER ROUTES */
    public static final String UMS_USER                             = UMS_PREFIX + "/user";
    public static final String UMS_USER_CREATE                      = UMS_PREFIX + "/user/create";
    public static final String UMS_USER_STORE                       = UMS_PREFIX + "/user/store";
    public static final String UMS_USER_SHOW                        = UMS_PREFIX + "/user/{id}";
    public static final String UMS_USER_EDIT                        = UMS_PREFIX + "/user/{id}/edit";
    public static final String UMS_USER_UPDATE                      = UMS_PREFIX + "/user/{id}/update";
    public static final String UMS_USER_DESTROY                     = UMS_PREFIX + "/user/{id}/destroy";

    /* UMS USER EDUCATION ROUTES */
    public static final String UMS_USER_EDUCATION                             = UMS_PREFIX + "/user-education";
    public static final String UMS_USER_EDUCATION_CREATE                      = UMS_PREFIX + "/user-education/create";
    public static final String UMS_USER_EDUCATION_STORE                       = UMS_PREFIX + "/user-education/store";
    public static final String UMS_USER_EDUCATION_SHOW                        = UMS_PREFIX + "/user-education/{id}";
    public static final String UMS_USER_EDUCATION_EDIT                        = UMS_PREFIX + "/user-education/{id}/edit";
    public static final String UMS_USER_EDUCATION_UPDATE                      = UMS_PREFIX + "/user-education/{id}/update";
    public static final String UMS_USER_EDUCATION_DESTROY                     = UMS_PREFIX + "/user-education/{id}/destroy";
    /* UMS ROUTES END */


    /*
     * DASHBOARD ROUTES END
     */
}
