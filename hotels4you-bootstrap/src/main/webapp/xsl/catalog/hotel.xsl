<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fmt="http://xml.apache.org/xalan/java/ru.com.m74.hotels4you.utils.Format"
                version="1.0">
    <xsl:import href="../commons/template.xsl"/>

    <xsl:template name="style">
        <link rel="stylesheet" href="{$cp}/css/catalog/hotel.css"/>
        <link rel="stylesheet" href="{$cp}/css/bootstrap-datepicker.min.css"/>
    </xsl:template>
    <xsl:template name="scripts">
        <script type="text/javascript" src="{$cp}/js/bootstrap-datepicker.min.js"/>
        <script type="text/javascript" src="{$cp}/js/bootstrap-datepicker.ru.js"/>
        <script type="text/javascript" src="{$cp}/js/hotel.js"/>
    </xsl:template>

    <xsl:template match="body">
        <div class="container">
            <xsl:apply-templates select="hotel"/>
        </div>
    </xsl:template>

    <xsl:template match="hotel">
        <div class="col-md-8">
            <xsl:call-template name="carousel"/>
        </div>

        <div class="col-md-4">
            <div class="page-header">
                <h3>
                    <xsl:value-of select="title"/>
                </h3>
            </div>
        </div>

        <div class="col-md-8">
            <ul class="nav nav-tabs">
                <li>
                    <a data-toggle="tab" href="#description">Описание</a>
                </li>
                <li class="active">
                    <a data-toggle="tab" href="#booking">Бронирование</a>
                </li>
                <li>
                    <a data-toggle="tab" href="#reviews">Отзывы</a>
                </li>
            </ul>

            <div class="tab-content">
                <div id="description" class="tab-pane fade">
                    <h3>Описание</h3>
                    <xsl:value-of select="description" disable-output-escaping="yes"/>
                </div>
                <div id="booking" class="tab-pane fade in active">
                    <h3>Стоимость проживания и наличие мест</h3>
                    <div class="sticky">
                        <hr/>
                        <div class="row">
                            <div class="form-group col-sm-3">
                                <label for="arrival">Заезд</label>
                                <input class="form-control datepicker" id="arrival" name="arrival"
                                       placeholder="DD.MM.YY" value="{../session/arrival}"/>
                            </div>
                            <div class="form-group col-sm-3">
                                <label for="arrival">Выезд</label>
                                <input class="form-control datepicker" id="departure"
                                       name="departure" placeholder="DD.MM.YY" value="{../session/departure}"/>
                            </div>
                        </div>
                        <div id="message-content"/>
                        <hr/>
                    </div>
                    <!-- <xsl:if test="not(../session/arrival) or not(../session/departure)">
                        <div class="alert alert-warning"> <strong>Внимание!</strong> <div> Для отображения
                        информации о наличии свободных номеров и стоимости проживания, необходимо
                        указать даты <mark>заезда</mark> и <mark>выезда</mark> . </div> </div> </xsl:if>
                        <xsl:apply-templates select="../session/message" /> <xsl:apply-templates
                        select="../session/exception" /> -->
                    <xsl:apply-templates select="room"/>
                    <hr/>
                </div>
                <div id="reviews" class="tab-pane fade">
                    <h3>Отзывы постояльцев</h3>
                    <p></p>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <h4>Ближайшие отели</h4>
        </div>
    </xsl:template>


    <xsl:template match="room">
        <div class="row">
            <div class="col-sm-3" style="margin-bottom: 10px;">
                <img class="" src="{iconUrl}" alt="{title}" style="width: 100%;"/>
            </div>
            <div class="col-sm-6">
                <h5 style="margin-top: 0px;">
                    <xsl:value-of select="title"/>
                </h5>
                <p class="small room-desc">
                    <xsl:value-of select="description" disable-output-escaping="yes"/>
                </p>
            </div>
            <div class="col-sm-3" style="text-align: center;">
                <div class="price">
                    <xsl:value-of select="fmt:currency(price)"/>
                    <xsl:text> руб.</xsl:text>
                </div>
                <form>
                    <input type="hidden" name="room" value="{id}"/>
                    <button class="btn btn-default" type="submit">Бронировать</button>
                </form>
            </div>
        </div>
        <hr/>
    </xsl:template>


    <xsl:template name="carousel">
        <div id="hotel-photos" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <xsl:for-each select="photo">
                    <li data-target="#hotel-photos" data-slide-to="{position() - 1}">
                        <xsl:attribute name="class">
                            <xsl:if test="position() = 1">active</xsl:if>
                        </xsl:attribute>
                    </li>
                </xsl:for-each>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <xsl:for-each select="photo">
                    <div>
                        <xsl:attribute name="class">
                            <xsl:text>item </xsl:text>
                            <xsl:if test="position() = 1">active</xsl:if>
                        </xsl:attribute>
                        <img src="http://www.h4y.ru/images/640x400/{filename}" alt="{title}"
                             width="100%"/>
                        <div class="carousel-caption">
                            <xsl:value-of select="title"/>
                        </div>
                    </div>
                </xsl:for-each>
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#hotel-photos" role="button"
               data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Предидущий</span>
            </a>
            <a class="right carousel-control" href="#hotel-photos" role="button"
               data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Следующий</span>
            </a>
        </div>
    </xsl:template>
</xsl:stylesheet>
