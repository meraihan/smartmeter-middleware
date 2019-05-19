function onChangeMeterDataLoad() {
    var meterModelNum = $("#meterModelNum").val();
    console.log(meterModelNum);
    $.ajax({
        type: "POST",
        dataType: "html",
        url: "/meter/search",
        data : {
            meterNumber: meterModelNum
        },
        success: function(response){
            $("#meterDatas").html(response);
        },
        error: function(xhr, error){
            console.debug(xhr); console.debug(error);
        }
    });
}

$(document).ready(function () {
    $("#btnSearch").click(function (e) {
        e.preventDefault();
        var meterModelNum = $("#meterModelNums").val();
        console.log(meterModelNum);
        $.ajax({
            type: "POST",
            dataType: "html",
            url: "/meter/search",
            data : {
                meterNumber: meterModelNum
            },
            success: function(response){
                $("#meterDatas").html(response);
            },
            error: function(xhr, error){
                console.debug(xhr); console.debug(error);
            }
        });
    });
});


Highcharts.chart('container', {
    title: {
        text: 'Weekly Power Consumption'
    },
    subtitle: {
        text: ''
    },
    xAxis: {
        categories: ['Week1', 'week2', 'week3', 'week4']
    },
    yAxis: {
        title: {
            text: 'KW'
        }
    },
    series: [{
        name: 'Flat',
        data: (function () {
            var arr = [];
            for (var i = 0; i < 4; i++) {
                var randNum = Math.round(Math.random() * 1000);
                arr.push([randNum]);
            }
            return arr;

        })()
    },{
        name: 'Peak',
        data: (function () {
            var arr = [];
            for (var i = 0; i < 4; i++) {
                var randNum = Math.round(Math.random() * 1000);
                arr.push([randNum]);
            }
            return arr;

        })()
    },{
        name: 'Off-Peak',
        data: (function () {
            var arr = [];
            for (var i = 0; i < 4; i++) {
                var randNum = Math.round(Math.random() * 1000);
                arr.push([randNum]);
            }
            return arr;

        })()
    }],
    exporting: {
        enabled: false
    },
    credits: {
        enabled: false
    }
});
Highcharts.chart('container1', {
    title: {
        text: 'Total Consumer Per Year'
    },
    subtitle: {
        text: ''
    },
    xAxis: {
        categories: ['2010', '2011', '2012', '2013', '2014', '2015', '2016', '2017', '2018']
    },
    yAxis: {
        title: {
            text: 'KW'
        }
    },
    series: [{
        type: 'column',
        name: 'Per Year Consumer',
        data: [25465,35146, 48964, 68974, 78964, 89646, 98146, 106486, 124635]
    }],
    exporting: {
        enabled: false
    },
    credits: {
        enabled: false
    }
});


Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

// Create the chart
Highcharts.stockChart('container2', {
    chart: {
        events: {
            load: function () {

                // set up the updating of the chart each second
                var series = this.series[0];
                setInterval(function () {
                    var x = (new Date()).getTime(), // current time
                        y = Math.round(Math.random() * 100);
                    series.addPoint([x, y], true, true);
                }, 1000);
            }
        }
    },

    rangeSelector: {
        buttons: [{
            count: 1,
            type: 'minute',
            text: '1M'
        }, {
            count: 5,
            type: 'minute',
            text: '5M'
        }, {
            type: 'all',
            text: 'ALL'
        }],
        inputEnabled: false,
        selected: 0
    },

    title: {
        text: 'Hourly Power Consumption'
    },

    subtitle: {
        text: document.ontouchstart === undefined ?
            'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
    },

    exporting: {
        enabled: false
    },

    series: [{
        name: 'KW',
        data: (function () {
            // generate an array of random data
            var data = [],
                time = (new Date()).getTime(),
                i;

            for (i = -999; i <= 0; i += 1) {
                data.push([
                    time + i * 1000,
                    Math.round(Math.random() * 100)
                ]);
            }
            return data;
        }())
    }],
    credits: {
        enabled: false
    }
});



var chart = Highcharts.chart('container3', {

    title: {
        text: 'Monthly Power Consumption'
    },

    subtitle: {
        text: 'Power Consumption'
    },

    xAxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },
    yAxis: [{
        title: {
            text: 'Consumption(Kw)',
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        }
    }],
    series: [{
        name: 'Consumption',
        type: 'column',
        colorByPoint: true,
        data: [2999.9, 5000.5, 4532.4, 5045.2, 4501.0, 5901.0, 6405.6, 7014.5, 7915.4, 8512.1, 9478.6, 6146.4],
        showInLegend: false,
        tooltip: {
            valueSuffix: ' Kw'
        }
    }],
    exporting: {
        enabled: false
    },
    credits: {
        enabled: false
    }
});


$('#plain').click(function () {
    chart.update({
        chart: {
            inverted: false,
            polar: false
        },
        subtitle: {
            text: 'Plain'
        }
    });
});

$('#inverted').click(function () {
    chart.update({
        chart: {
            inverted: true,
            polar: false
        },
        subtitle: {
            text: 'Inverted'
        }
    });
});

$('#polar').click(function () {
    chart.update({
        chart: {
            inverted: false,
            polar: true
        },
        subtitle: {
            text: 'Polar'
        }
    });
});

//
// $.getJSON(
//     'https://cdn.rawgit.com/highcharts/highcharts/057b672172ccc6c08fe7dbb27fc17ebca3f5b770/samples/data/usdeur.json',
//     function (data) {
//
//         Highcharts.chart('container2', {
//             chart: {
//                 zoomType: 'x'
//             },
//             title: {
//                 text: 'Yearly Power Consumption'
//             },
//             subtitle: {
//                 text: document.ontouchstart === undefined ?
//                     'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
//             },
//             xAxis: {
//                 title: {
//                     enabled: true,
//                     text: 'Year'
//                 },
//                 type: 'datetime'
//             },
//             yAxis: {
//                 title: {
//                     text: 'KW'
//                 }
//             },
//             legend: {
//                 enabled: false
//             },
//             plotOptions: {
//                 area: {
//                     fillColor: {
//                         linearGradient: {
//                             x1: 0,
//                             y1: 0,
//                             x2: 0,
//                             y2: 1
//                         },
//                         stops: [
//                             [0, Highcharts.getOptions().colors[0]],
//                             [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
//                         ]
//                     },
//                     marker: {
//                         radius: 2
//                     },
//                     lineWidth: 1,
//                     states: {
//                         hover: {
//                             lineWidth: 1
//                         }
//                     },
//                     threshold: null
//                 }
//             },
//
//             series: [{
//                 // type: 'area',
//                 name: 'kw/h data',
//                 data: data
//             }],
//             credits: {
//                 enabled: false
//             }
//         });
//     }
// );

function autocomplete(inp, arr) {
    /*the autocomplete function takes two arguments,
     the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    inp.addEventListener("input", function(e) {
        var a, b, i, val = this.value;
        /*close any already open lists of autocompleted values*/
        closeAllLists();
        if (!val) { return false;}
        currentFocus = -1;
        /*create a DIV element that will contain the items (values):*/
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        /*append the DIV element as a child of the autocomplete container:*/
        this.parentNode.appendChild(a);
        /*for each item in the array...*/
        for (i = 0; i < arr.length; i++) {
            /*check if the item starts with the same letters as the text field value:*/
            if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                /*create a DIV element for each matching element:*/
                b = document.createElement("DIV");
                /*make the matching letters bold:*/
                b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                b.innerHTML += arr[i].substr(val.length);
                /*insert a input field that will hold the current array item's value:*/
                b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                /*execute a function when someone clicks on the item value (DIV element):*/
                b.addEventListener("click", function(e) {
                    /*insert the value for the autocomplete text field:*/
                    inp.value = this.getElementsByTagName("input")[0].value;
                    /*close the list of autocompleted values,
                     (or any other open lists of autocompleted values:*/
                    closeAllLists();
                });
                a.appendChild(b);
            }
        }
    });
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function(e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x) x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
             increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
             decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x) x[currentFocus].click();
            }
        }
    });
    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x) return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }
    function removeActive(x) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }
    function closeAllLists(elmnt) {
        /*close all autocomplete lists in the document,
         except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }
    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}

/*An array containing all the country names in the world:*/
var countries = ["Dhanmondi","Zigatola","Mohammadpur","Shyamoli","Farmgate","Maghbazar", "Motijeel", "Mirpur", "Gulshan", "Banani", "Uttara",
"Mohakhali", "Rampura", "Badda"];

/*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
autocomplete(document.getElementById("myInput"), countries);

$("#myInput").keyup(function() {
    console.log($(this).val());

});

