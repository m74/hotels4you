<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE xsl:stylesheet [
        <!ENTITY nbsp "&#160;">
        <!ENTITY times "&#215;">
        ]>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" encoding="utf-8" indent="yes"/>

    <xsl:include href="html.xsl"/>

    <xsl:variable name="cp" select="/document/@contextPath"/>
    <xsl:variable name="uri" select="/document/@uri"/>
    <xsl:variable name="pathInfo" select="/document/@pathInfo"/>

    <xsl:template match="body">
        <xsl:call-template name="content"/>
    </xsl:template>

    <xsl:template match="/document">
        <xsl:text disable-output-escaping="yes"><![CDATA[<!DOCTYPE html>]]></xsl:text>
        <html lang="ru">
            <head>
                <xsl:call-template name="head"/>
            </head>
            <body>
                <div class="modal"/>
                <xsl:call-template name="navbar"/>
                <div id="messages"/>
                <xsl:call-template name="path"/>
                <xsl:apply-templates select="body"/>
                <xsl:call-template name="content"/>
                <xsl:call-template name="footer"/>
                <xsl:call-template name="bag"/>
                <script src="{$cp}/js/jquery-2.1.4.min.js" type="text/javascript"/>
                <script src="{$cp}/js/bootstrap.min.js" type="text/javascript"/>
                <script src="{$cp}/js/pnotify.custom.min.js" type="text/javascript"/>
                <script src="{$cp}/js/jquery.validate.min.js" type="text/javascript"/>
                <script src="{$cp}/js/jquery.validate.ru.js" type="text/javascript"/>
                <script src="{$cp}/js/site.js" type="text/javascript"/>
                <xsl:call-template name="scripts"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="content">
        <xsl:apply-templates select="content"/>
    </xsl:template>

    <xsl:template name="style">
        <xsl:apply-templates select="style"/>
    </xsl:template>

    <xsl:template name="scripts">
        <xsl:apply-templates select="script"/>
    </xsl:template>

    <xsl:template name="head">
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <link rel="stylesheet" href="{$cp}/css/normalize.css"/>
        <link rel="stylesheet" href="{$cp}/css/bootstrap1.min.css"/>
        <link rel="stylesheet" href="{$cp}/css/stickyfooter.css"/>
        <link rel="stylesheet" href="{$cp}/css/pnotify.custom.min.css"/>
        <link rel="stylesheet" href="{$cp}/css/animate.css"/>
        <title>
            <xsl:text>Гостиницы для Вас - </xsl:text>
            <xsl:value-of select="title"/>
        </title>

        <meta http-equiv="Content-Language" content="ru"/>
        <xsl:call-template name="style"/>

    </xsl:template>

    <xsl:template match="navigation/link">
        <li>
            <xsl:attribute name="class">
                <xsl:if test="@uri = $uri">active</xsl:if>
            </xsl:attribute>
            <a href="{$cp}{@uri}">
                <xsl:value-of select="."/>
            </a>
        </li>
    </xsl:template>


    <xsl:template match="path/elem">
        <xsl:choose>
            <xsl:when test="position() != last()">
                <li>
                    <a href="{$cp}{@href}">
                        <xsl:value-of select="."/>
                        <xsl:if test=". = ''">
                            <xsl:value-of
                                    select="document(concat(@href, 'index.xml'))/document/pathname"/>
                        </xsl:if>
                    </a>
                </li>
            </xsl:when>
            <xsl:otherwise>
                <li class="active">
                    <xsl:value-of select="."/>
                    <xsl:if test=". = ''">
                        <xsl:value-of select="/document/pathname"/>
                    </xsl:if>
                </li>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="path">
        <div class="path container">
            <ol class="breadcrumb">
                <xsl:apply-templates select="//path/elem"/>
            </ol>
        </div>
    </xsl:template>

    <xsl:template name="navbar">
        <div class="container" style="padding: 5px;">+7 (495) 223-3856</div>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed"
                            data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                            aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"/>
                        <span class="icon-bar"/>
                        <span class="icon-bar"/>
                    </button>

                    <a class="navbar-brand" href="{$cp}">HOTELS4YOU</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <xsl:apply-templates
                                select="document('/WEB-INF/nav.xml')/navigation/link"/>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <xsl:attribute name="class">
                                <xsl:if test="@uri = '/mybooking/'">active</xsl:if>
                            </xsl:attribute>
                            <a href="{$cp}/mybooking/">
                                <xsl:text> Мое бронирование </xsl:text>
                                <span class="badge">
                                    <xsl:value-of select="session/booking.badge"/>
                                </span>
                            </a>
                        </li>

                        <xsl:choose>
                            <xsl:when test="session/user.id">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                       role="button" aria-haspopup="true" aria-expanded="false">
                                        <!-- <span class="glyphicon glyphicon-user" aria-hidden="true"
                                            /> -->
                                        <img src="{session/user.avatar}" class="img-circle"
                                             style="margin: -16px 0px -13px; height: 44px;"/>
                                        &nbsp;
                                        <xsl:value-of select="session/user.name"/>
                                        &nbsp;
                                        <span class="caret"/>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="{$cp}/profile/">
                                                <xsl:value-of
                                                        select="document('/profile/index.xml')/document/pathname"/>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="{$cp}/mybooking/">
                                                <xsl:value-of
                                                        select="document('/mybooking/index.xml')/document/pathname"/>
                                            </a>
                                        </li>
                                        <li role="separator" class="divider"/>
                                        <li>
                                            <a href="{$cp}/logout">
                                                <xsl:text> Выход </xsl:text>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </xsl:when>
                            <xsl:otherwise>
                                <li>
                                    <a href="{$cp}/login">
                                        <!-- <span class="glyphicon glyphicon-user" aria-hidden="true"
                                            /> -->
                                        <xsl:text> Авторизация </xsl:text>
                                    </a>
                                </li>
                            </xsl:otherwise>
                        </xsl:choose>

                    </ul>
                </div>
            </div>
        </nav>
    </xsl:template>

    <xsl:template name="bag">
        <div class="bag"></div>
    </xsl:template>

    <xsl:template name="footer">
        <footer class="footer">
            <div class="container">
                <p class="text-muted">Бронирование гостиниц Москвы и России. Забронировать
                    номер гостиниц и мини-отелей онлайн.
                </p>
            </div>
        </footer>
    </xsl:template>

    <xsl:template match="session/message">
        <div class="alert alert-warning" role="alert">
            <button type="button" class="close" data-dismiss="alert"
                    aria-label="Close">
                <span aria-hidden="true">&times;
                </span>
            </button>
            <xsl:apply-templates/>
        </div>
    </xsl:template>
    <xsl:template match="session/error">
        <div class="alert alert-danger" role="alert">
            <button type="button" class="close" data-dismiss="alert"
                    aria-label="Close">
                <span aria-hidden="true">&times;
                </span>
            </button>
            <xsl:apply-templates/>
        </div>
    </xsl:template>

</xsl:stylesheet>
