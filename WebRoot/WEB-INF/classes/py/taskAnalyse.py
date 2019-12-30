# -*- coding: utf-8 -*-

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
from sklearn.externals import joblib
from sys import argv

# 使用的特征列
FEAT_COLS = []
# 结果列
RESULTCOL = []
#资源文件
RES_PATH = ""





def build_param():
    """
    构建参数
    """
    global RES_PATH
    #读取列信息
    global FEAT_COLS
    global RESULTCOL
    RES_PATH = argv[2]
    heads=argv[1]
    FEAT_COLS = heads.split(',')
    RESULTCOL = FEAT_COLS[-1] 
    del FEAT_COLS[-1]
    #print("RES_PATH:",RES_PATH)
    #print("特征数据列:",FEAT_COLS)
    #print("结果列:",RESULTCOL)




def main():
    """
        主函数
    """
    #待生成的训练模型
    csv_data = pd.read_csv(RES_PATH, usecols=FEAT_COLS + [RESULTCOL])
    X = csv_data[FEAT_COLS].values
    y = csv_data[RESULTCOL].values    
    # 分割数据集
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=1 / 3, random_state=10)

    # 建立线性回归模型
    linear_reg_model = LinearRegression()
    
    # 模型训练
    linear_reg_model.fit(X_train, y_train) 
    print(linear_reg_model.coef_)  
    print(linear_reg_model.intercept_)


if __name__ == '__main__':
     build_param()
     main()


