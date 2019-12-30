# -*- coding: utf-8 -*-
from sys import argv


def main():
    print('avg[1]:', argv[1])
    print('avg[2]:', argv[2])
    print('avg[3]:', argv[3])
    arg=argv[3]
    FEAT_COLS = arg.split(',')
    RESULTCOL = FEAT_COLS[-1]
    del FEAT_COLS[-1]
    print('FEAT_COLS:',FEAT_COLS)
    print('RESULTCOL:',RESULTCOL)




if __name__ == '__main__':
     main()
  
