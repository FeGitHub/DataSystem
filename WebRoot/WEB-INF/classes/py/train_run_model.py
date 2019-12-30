# -*- coding: utf-8 -*-

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
from sklearn.externals import joblib
from sys import argv


def load_and_predict(single_test_feat):
    MODEL_FILE=argv[2]+"\\project_model.m"
    linear_reg_model = joblib.load(MODEL_FILE)
    y_pred = linear_reg_model.predict([single_test_feat])
    print('完成时间误差预测:', y_pred)


if __name__ == '__main__':
    arg=argv[3]
    arr = arg.split(',')
    arr=list(map(int,arr))
    print(arr)
    load_and_predict(arr)