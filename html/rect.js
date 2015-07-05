function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min)) + min;
}

$(document).ready(function(){
  // Determine the height and width of the container
  var containerHeight = $('#container').height();
  var containerWidth = $('#container').width();
  console.log("Container height is: " + containerHeight);
  console.log("Container widthheight is: " + containerWidth);


  // Determine the number of rectangles that will be added to the page
  var numRect = getRandomInt(5, 15);
  console.log("Will be creating " + numRect + " rectangles.");
  for(var i = 0; i < numRect; i++) {

    // Pick a random size between 50 and 200
    var height = getRandomInt(50, 100);
    var width = getRandomInt(50, 100);

    // Pick a random position
    var left = getRandomInt(0, containerWidth - width);
    var top = getRandomInt(0, containerHeight - height);


    $('<div></div>')
      .addClass('rect')
      .css({
        'height': height + 'px',
        'width': width + 'px',
        'top': top + 'px',
        'left': left + 'px'
      })
      .appendTo($('#container'));
  }
});
