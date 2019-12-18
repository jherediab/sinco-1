var MENU_POS0 = [
// Level 0 block configuration
{
	// Item's height in pixels
	'height'     : 23,
	// Item's width in pixels
	'width'      : 120,
	// if Block Orientation is vertical
	'vertical'   : false,
	// Block outing table parameters:
	// [cellpadding, cellspacing, border]
	'table'      : [0, 0, 0],
	// Time Delay in milliseconds before o_block block expands
	// after mouse pointer overs an item
	'expd_delay' : 200,
	// Time Delay in milliseconds before menu collapses after mouse
	// pointer leafs all items
	'hide_delay' : 300,
	// Style class names for the level
	'css' : {
		// Block outing table class
		'table' : 'mtable',
		// Item outer tag style class for all item states or
		// classes for [<default state>, <hovered state>, <clicked state>]
		'outer' : 'mouter',
		// Item inner tag style class for all item states or
		// classes for [<default state>, <hovered state>, <clicked state>]
		'inner' : 'minner'
	}
},
// Level 1 block configuration
{
	'width'      : 150,
	// Vertical Offset between adjacent levels in pixels
	'block_top'  : 24,
	// Horizontal Offset between adjacent levels in pixels
	'block_left' : 0,
	// block behaviour if single frame:	
	// 1 - shift to the edge, 2 - flip relatively to left upper corner
	'wise_pos'   : 2,
	'vertical'   : true,
	'transition' : [ 'progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=forward,enabled=0,Duration=.5)'],
	// level shadow scope settings
	'shadow' : {
		// horisontal offset of the shadow in pixels
		'offX' : 4,
	// vertical offset of the shadow in pixels
		'offY' : 3,
		// color of the shadow
		'color' : '#777777'
	},
	'opacity' : 96,
	// Style class names for the level
	'css' : {
		// Block outing table class
		'table' : 'm1table2',
		// Item outer tag style class for all item states or
		// classes for [<default state>, <hovered state>, <clicked state>]
		'outer' : ['m1out2', 'm1over2', 'm1over2'],
		// Item inner tag style class for all item states or
		// classes for [<default state>, <hovered state>, <clicked state>]
		'inner' : 'minner2'
	}
},
// Level 2 block configuration
{
	'width'      : 120,
	'block_top'  : 11,
	'block_left' : 100
},
// Level 3 block configuration
{
	'block_left' : 195
}
	//rest level configurations are inherited from level 3
]

